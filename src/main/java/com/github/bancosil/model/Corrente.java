package com.github.bancosil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Corrente")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Corrente extends Account {
    public Corrente(String name, String password, String email, String cpf) {
        super(name, password, email, cpf);
    }
}

