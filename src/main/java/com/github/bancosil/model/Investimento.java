package com.github.bancosil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("investmento")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Investimento extends Account {
    public Investimento(String name, String password, String email, String cpf) {
        super(name, password, email, cpf);
    }
}
