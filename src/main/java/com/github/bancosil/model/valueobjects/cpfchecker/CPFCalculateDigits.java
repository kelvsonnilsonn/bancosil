package com.github.bancosil.model.valueobjects.cpfchecker;

import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;

class CPFCalculateDigits {

    private static int validateFirstDigit(String cpf) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int rest = sum % 11;

        return (rest < 2) ? 0 : 11 - rest;
    }

    private static int validateSecondDigit(String cpf) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int rest = sum % 11;

        return (rest < 2) ? 0 : 11 - rest;
    }

    public static void validateDigits(String cpf) {
        int first_validator_digit = validateFirstDigit(cpf);
        int second_validator_digit = validateSecondDigit(cpf);

        if (Character.getNumericValue(cpf.charAt(9)) != first_validator_digit
                || (Character.getNumericValue(cpf.charAt(10)) != second_validator_digit)) {
            throw new InvalidCPFNumberException();
        }
    }
}