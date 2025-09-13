package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CPF {
    private String cpf;

    private static final String CPF_REGEX = "^\\d{3}.?\\d{3}.?\\d{3}-?\\d{2}$";

    private static final Pattern pattern = Pattern.compile(CPF_REGEX);

    public CPF(String cpf){
        this.cpf = validate(cpf);
    }

    public static CPF of(String cpf){
        return new CPF(cpf);
    }

    private String validate(String cpf){
        Objects.requireNonNull(cpf, "O CPF não pode ser nulo.");
        if(!pattern.matcher(cpf).matches()){
            throw new InvalidCPFNumberException();
        }
        return cpf;
    }
}
