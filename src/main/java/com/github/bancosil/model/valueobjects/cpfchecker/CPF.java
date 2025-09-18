package com.github.bancosil.model.valueobjects.cpfchecker;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CPF {
    @Column(name = "cpf", unique = true)
    private String cpf;

    public CPF(String cpf){
        this.cpf = CPFFormatter.validateFormatter(cpf);;
        CPFCalculateDigits.validateDigits(this.cpf);
    }

    public static CPF of(String cpf){
        return new CPF(cpf);
    }
}
