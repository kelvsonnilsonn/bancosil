package com.github.bancosil.service.operation;

import com.github.bancosil.model.Account;
import com.github.bancosil.service.validator.OperationValidator;

import java.math.BigDecimal;

public class TransferPix implements TransferOperation{
    @Override
    public void execute(Account sender, Account receiver, BigDecimal amount) {
        OperationValidator.validate(amount);
        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    @Override
    public OperationType operationType() {
        return OperationType.TRANSFER_PIX;
    }
}
