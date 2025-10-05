package com.github.bancosil.model;

import com.github.bancosil.enums.AccountType;
import com.github.bancosil.exception.operational.InsufficientBalanceException;
import com.github.bancosil.model.valueobjects.*;
import com.github.bancosil.model.valueobjects.cpfchecker.CPF;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private CPF cpf;

    @Embedded
    private Address address;

    private BigDecimal money;

    private LocalDateTime createdAt;

    public Account(Username username, Password password, Email email, CPF cpf, AccountType type){
        this.username = username;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
        this.type = type;
        this.address = new Address("Não definida", "Não definida", "Não definida", 0);
        this.money = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
    }

    public void selectAddress(String city, String street, String zipcode, int number){
        this.address = Address.of(city, street, zipcode, number);
    }

    public String getUsername(){
        return username.getUsername();
    }

    public String getEmail(){
        return email.getEmail();
    }

    public String getCPF(){
        return cpf.getCpf();
    }

    public String getPassword(){return password.getPassword(); }

    public void withdraw(BigDecimal amount) {
        if (this.money.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.money = money.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.money = money.add(amount);
    }
}
