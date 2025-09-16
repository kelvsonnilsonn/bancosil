package com.github.bancosil.application;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import com.github.bancosil.model.Poupanca;
import com.github.bancosil.model.Investimento;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.service.OperationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ApplicationRunner {

    @Autowired
    private AccountConfigurations accountConfig;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationalService operationalService;

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean authenticated = accountConfig.isLogged();

        while (!authenticated) {
            System.out.println("\n=== BEM-VINDO AO BANCO SIL ===");
            System.out.println("1. Login");
            System.out.println("2. Registrar nova conta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int initialChoice = scanner.nextInt();
            scanner.nextLine();

            switch (initialChoice) {
                case 1 -> {
                    login();
                    System.out.println("Login bem-sucedido!");
                    showMainMenu();
                }
                case 2 -> showRegisterMenu();
                case 0 -> {
                    System.exit(1);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void showRegisterMenu(){
        System.out.println("\n=== BANCO SIL ===");
        System.out.println("1. Criar Conta Corrente");
        System.out.println("2. Criar Conta Poupança");
        System.out.println("3. Criar Conta Investimento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        switch (choice) {
            case 1 -> createAccount("CORRENTE");
            case 2 -> createAccount("POUPANCA");
            case 3 -> createAccount("INVESTIMENTO");
            case 0 -> System.exit(0);
            default -> System.out.println("Opção inválida!");
        }
    }


    private void showMainMenu() {
        Account currentUser = accountConfig.getCurrentUser();
        boolean inMainMenu = true;

        while (inMainMenu && accountConfig.isLogged()) {
            System.out.println("\n=== BANCO SIL - Bem-vindo, " + currentUser.getUsername() + " ===");
            System.out.println("Saldo: R$ " + currentUser.getMoney());
            System.out.println("Tipo de Conta: " + currentUser.getClass().getSimpleName());
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Transferir PIX");
            System.out.println("4. Ver Informações da Conta");
            System.out.println("5. Configurar Endereço");
            System.out.println("6. Logout");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (choice) {
                case 1 -> deposit();
                case 2 -> withdraw();
                case 3 -> transferPix();
                case 4 -> showAccountInfo();
                case 5 -> configureAddress();
                case 6 -> {
                    inMainMenu = false;
                    logout();
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void login() {
        try {
            System.out.println("\n=== LOGIN ===");
            System.out.print("Usuário: ");
            String username = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            accountConfig.login(username, password);

            if (accountConfig.isLogged()) {
                System.out.println("Login bem-sucedido!");
            } else {
                System.out.println("Login falhou! Verifique suas credenciais.");
            }

        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }

    private void createAccount(String accountType) {
        try {
            System.out.println("\n=== CRIAR CONTA " + accountType + " ===");
            System.out.print("Nome de usuário: ");
            String username = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            Account account;
            switch (accountType) {
                case "CORRENTE" -> account = new Corrente(username, password, email, cpf);
                case "POUPANCA" -> account = new Poupanca(username, password, email, cpf);
                case "INVESTIMENTO" -> account = new Investimento(username, password, email, cpf);
                default -> throw new IllegalArgumentException("Tipo de conta inválido");
            }

            accountService.create(account);
            System.out.println("Conta " + accountType.toLowerCase() + " criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    private void deposit() {
        try {
            System.out.println("\n=== DEPÓSITO ===");
            System.out.print("Valor para depósito: R$ ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // Consumir quebra de linha

            Account currentUser = accountConfig.getCurrentUser();
            operationalService.deposit(currentUser, amount);

            Account updatedUser = accountService.findById(currentUser.getId());

            System.out.println("Depósito de R$ " + amount + " realizado com sucesso!");
            System.out.println("Novo saldo: R$ " + updatedUser.getMoney());

        } catch (Exception e) {
            System.out.println("Erro no depósito: " + e.getMessage());
        }
    }

    private void withdraw() {
        try {
            System.out.println("\n=== SAQUE ===");
            System.out.print("Valor para saque: R$ ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // Consumir quebra de linha

            Account currentUser = accountConfig.getCurrentUser();
            BigDecimal withdrawn = operationalService.withdraw(currentUser, amount);

            Account updatedUser = accountService.findById(currentUser.getId());

            System.out.println("Saque de R$ " + withdrawn + " realizado com sucesso!");
            System.out.println("Novo saldo: R$ " + updatedUser.getMoney());

        } catch (Exception e) {
            System.out.println("Erro no saque: " + e.getMessage());
        }
    }

    private void transferPix() {
        try {
            System.out.println("\n=== TRANSFERÊNCIA PIX ===");
            System.out.print("ID da conta destino: ");
            Long receiverId = scanner.nextLong();
            scanner.nextLine(); // Consumir quebra de linha

            System.out.print("Valor para transferência: R$ ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // Consumir quebra de linha

            Account sender = accountConfig.getCurrentUser();
            Account receiver = accountService.findById(receiverId);

            operationalService.transferPix(sender, receiver, amount);

            Account updatedSender = accountService.findById(sender.getId());

            System.out.println("Transferência PIX de R$ " + amount + " para " + receiver.getUsername() + " realizada com sucesso!");
            System.out.println("Novo saldo: R$ " + updatedSender.getMoney());

        } catch (Exception e) {
            System.out.println("Erro na transferência: " + e.getMessage());
        }
    }

    private void showAccountInfo() {
        Account account = accountConfig.getCurrentUser();

        System.out.println("\n=== INFORMAÇÕES DA CONTA ===");
        System.out.println("ID: " + account.getId());
        System.out.println("Usuário: " + account.getUsername());
        System.out.println("Tipo de Conta: " + account.getClass().getSimpleName());
        System.out.println("Saldo: R$ " + account.getMoney());
        System.out.println("Email: " + account.getEmail());
        System.out.println("CPF: " + account.getCPF());

        if (account.getAddress() != null && !account.getAddress().getStreet().equals("Não definida")) {
            System.out.println("Endereço: " + account.getAddress());
        } else {
            System.out.println("Endereço: Não configurado");
        }
    }

    private void configureAddress() {
        try {
            System.out.println("\n=== CONFIGURAR ENDEREÇO ===");
            System.out.print("Cidade: ");
            String city = scanner.nextLine();

            System.out.print("Rua: ");
            String street = scanner.nextLine();

            System.out.print("CEP: ");
            String zipcode = scanner.nextLine();

            System.out.print("Número: ");
            int number = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            Account currentUser = accountConfig.getCurrentUser();
            currentUser.selectAddress(city, street, zipcode, number);

            accountService.update(currentUser);

            Account updatedUser = accountService.findById(currentUser.getId());

            System.out.println("Endereço configurado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao configurar endereço: " + e.getMessage());
        }
    }

    private void logout() {
        accountConfig.logout();
        System.out.println("Logout realizado com sucesso!");
    }
}