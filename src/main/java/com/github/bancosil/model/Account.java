package com.github.bancosil.model;

import com.github.bancosil.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username name;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    @JoinColumn(name = "CPF", unique = true)
    private CPF cpf;

    @Embedded
    private Address address;

    private BigDecimal money;

    public BigDecimal withdraw(BigDecimal amount) {
        this.money = money.subtract(amount);
        return amount;
    }

    public void deposit(BigDecimal amount) {
        this.money = money.add(amount);
    }
}
