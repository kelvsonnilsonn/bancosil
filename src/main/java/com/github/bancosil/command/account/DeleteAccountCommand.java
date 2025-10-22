package com.github.bancosil.command.account;

import jakarta.validation.constraints.NotNull;

public record DeleteAccountCommand(
        @NotNull Long id,
        String reason
) {
}
