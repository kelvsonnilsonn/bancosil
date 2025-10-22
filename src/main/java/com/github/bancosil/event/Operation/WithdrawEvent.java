package com.github.bancosil.event.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WithdrawEvent(
        Long accountId,
        BigDecimal amount,
        LocalDateTime createAt
) {
    public WithdrawEvent(Long accountId, BigDecimal amount){
        this(accountId, amount, LocalDateTime.now());
    }
}
