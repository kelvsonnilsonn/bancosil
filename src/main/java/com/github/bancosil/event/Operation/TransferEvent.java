package com.github.bancosil.event.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferEvent(
        Long senderId,
        Long receiverId,
        BigDecimal amount,
        LocalDateTime createAt
) {
    public TransferEvent(Long senderId, Long receiverId, BigDecimal amount){
        this(senderId, receiverId, amount, LocalDateTime.now());
    }
}
