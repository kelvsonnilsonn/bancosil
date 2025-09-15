package com.github.bancosil.service.operation;

import com.github.bancosil.model.Account;

import java.math.BigDecimal;

public interface TransferOperation extends BasicBankOperation{
    void execute(Account sender, Account receiver, BigDecimal amount);
}
