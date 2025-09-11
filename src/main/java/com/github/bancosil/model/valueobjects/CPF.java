package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CPF {
    private String cpf;

    public CPF(String cpf){
        Objects.requireNonNull(cpf);
        this.cpf = cpf;
    }

    public CPF of(String cpf){
        return new CPF(cpf);
    }
}
