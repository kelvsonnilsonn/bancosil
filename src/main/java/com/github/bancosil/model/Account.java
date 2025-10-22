package com.github.bancosil.model;

import com.github.bancosil.enums.AccountType;
import com.github.bancosil.enums.UserRole;
import com.github.bancosil.exception.operational.InsufficientBalanceException;
import com.github.bancosil.model.valueobjects.UserInformation;
import com.github.bancosil.model.valueobjects.account.Address;
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

    @Embedded
    private UserInformation userInformation;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Embedded
    private Address address;

    private BigDecimal money;

    private LocalDateTime createdAt;

    public Account(UserInformation userInformation, AccountType type){
        this.userInformation = userInformation;
        this.type = type;
        this.address = new Address("Não definida", "Não definida", "Não definida", 0);
        this.money = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
    }

    public String getUsername(){
        return userInformation.getUsername();
    }

    public String getEmail(){
        return userInformation.getEmail();
    }

    public String getPassword(){ return userInformation.getPassword(); }

    public UserRole getRole(){ return userInformation.getRole(); }

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
