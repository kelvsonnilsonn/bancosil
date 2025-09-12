package com.github.bancosil.model;

import com.github.bancosil.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

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

    public Account(String username, String password, String email, String cpf){
        this.username = new Username(username);
        this.password = new Password(password);
        this.email = new Email(email);
        this.cpf = new CPF(cpf);
        this.address = new Address("Não definida", "Não definida", "Não definida", 0);
        this.money = BigDecimal.ZERO;
    }

    public void selectAddress(String city, String street, String zipcode, int number){
        this.address = Address.of(city, street, zipcode, number);
    }

    public void withdraw(BigDecimal amount) {
        this.money = money.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.money = money.add(amount);
    }
}
