package com.github.bancosil.service;

import com.github.bancosil.command.account.CreateAccountCommand;
import com.github.bancosil.dto.AuthResponseDTO;
import com.github.bancosil.command.auth.LoginCommand;
import com.github.bancosil.enums.AccountType;
import com.github.bancosil.event.account.CreateAccountEvent;
import com.github.bancosil.exception.AccountAlreadyExistsException;
import com.github.bancosil.exception.FailedLoginAttemptException;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.valueobjects.UserInformation;
import com.github.bancosil.model.valueobjects.account.Email;
import com.github.bancosil.model.valueobjects.account.Password;
import com.github.bancosil.model.valueobjects.account.Username;
import com.github.bancosil.model.valueobjects.cpfchecker.CPF;
import com.github.bancosil.repository.AccountRepository;
import com.github.bancosil.security.TokenService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EventStoreService eventStoreService;

    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginCommand dto){
        Account account = accountRepository.findByUsername(dto.username()).orElseThrow(AccountNotFoundException::new);
        if(!(passwordEncoder.matches(dto.password(), account.getPassword()))){
            throw new FailedLoginAttemptException();
        }
        String token = tokenService.generateToken(account);
        return new AuthResponseDTO(token, account.getUsername());
    }

    public AuthResponseDTO register(CreateAccountCommand command){
        Optional<Account> account = accountRepository.findByUsername(command.username());
        if(account.isPresent()){
            throw new AccountAlreadyExistsException();
        }
        Account newAccount = createAccount(command);
        Account savedAccount = accountRepository.save(newAccount);
        String token = tokenService.generateToken(newAccount);
        CreateAccountEvent event = new CreateAccountEvent(savedAccount.getId(), newAccount.getUsername());
        eventStoreService.saveEvent(AppConstants.ACCOUNT, savedAccount.getId(), event);
        return new AuthResponseDTO(token, newAccount.getUsername());
    }

    private Account createAccount(CreateAccountCommand command){
        Username username = new Username(command.username());
        Password password = Password.of(command.password(), passwordEncoder);
        CPF cpf = new CPF(command.cpf());
        AccountType type = AccountType.valueOf(command.type().toUpperCase());
        Email email = new Email(command.email());
        UserInformation userInformation = new UserInformation(username, password, email, cpf);
        return new Account(userInformation, type);
    }
}
