package com.github.bancosil.event.account;

import java.time.LocalDateTime;

public record CreateAccountEvent(
        Long accountId,
        String username,
        LocalDateTime createdAt
) {
    public CreateAccountEvent(Long accountId, String username){
        this(accountId, username, LocalDateTime.now());
    }
}
