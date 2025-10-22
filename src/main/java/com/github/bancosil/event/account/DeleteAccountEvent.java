package com.github.bancosil.event.account;

import java.time.LocalDateTime;

public record DeleteAccountEvent(
        Long accountId,
        String reason,
        Long admin,
        LocalDateTime createAt
) {
    public DeleteAccountEvent(Long accountId, String reason, Long admin){
        this(accountId, reason, admin, LocalDateTime.now());
    }
}
