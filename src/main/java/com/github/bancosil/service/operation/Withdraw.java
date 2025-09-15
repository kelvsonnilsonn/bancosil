package com.github.bancosil.service.operation;

import com.github.bancosil.model.Account;
import com.github.bancosil.service.validator.OperationValidator;

import java.math.BigDecimal;

public class Withdraw implements Operation{
    @Override
    public void execute(Account account, BigDecimal amount) {
        OperationValidator.validate(amount);
        account.withdraw(amount);
    }

    @Override
    public OperationType operationType() {
        return OperationType.WITHDRAW;
    }
}
