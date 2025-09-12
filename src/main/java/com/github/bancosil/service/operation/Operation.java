package com.github.bancosil.service.operation;

import com.github.bancosil.model.Account;

import java.math.BigDecimal;

public interface Operation {
    void execute(Account account, BigDecimal amount);
}
