package com.github.bancosil.service.operation;

import com.github.bancosil.model.Account;

import java.math.BigDecimal;

public class Withdraw implements Operation{
    @Override
    public void execute(Account account, BigDecimal amount) {
        account.withdraw(amount);
    }

    @Override
    public OperationType operationType() {
        return OperationType.WITHDRAW;
    }
}
