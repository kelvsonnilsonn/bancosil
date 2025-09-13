package com.github.bancosil.model.valueobjects.cpfchecker;

import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;

import java.util.Objects;
import java.util.regex.Pattern;

class CPFFormatter {

    private static final String CPF_REGEX = "^\\d{3}[.]?\\d{3}[.]?\\d{3}-?\\d{2}$";

    private static final Pattern pattern = Pattern.compile(CPF_REGEX);

    private static String clearCPF(String cpf) {
        Objects.requireNonNull(cpf, "O CPF não deve se null.");
        return cpf.replaceAll("[^0-9]", "");
    }

    private static void validLength(String cpf) {
        if (cpf.length() != 11) {
            throw new InvalidCPFNumberException();
        }
    }

    private static void validSequence(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new InvalidCPFNumberException();
        }
    }
    private static void validRegex(String cpf){
        if (!pattern.matcher(cpf).matches()){
            throw new InvalidCPFNumberException();
        }
    }

    public static String validateFormatter(String cpf) {
        validRegex(cpf);
        String clearedCPF = clearCPF(cpf);
        validSequence(clearedCPF);
        validLength(clearedCPF);

        return clearedCPF;
    }
}