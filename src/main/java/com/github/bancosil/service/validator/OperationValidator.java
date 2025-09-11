package com.github.bancosil.service.validator;

import com.github.bancosil.exception.operational.NegativeOperationException;

import java.math.BigDecimal;
import java.util.Objects;

public class OperationValidator {

    public static void validate(BigDecimal amount){
        Objects.requireNonNull(amount, "Valor não pode ser nulo.");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeOperationException();
        }
    }
}
