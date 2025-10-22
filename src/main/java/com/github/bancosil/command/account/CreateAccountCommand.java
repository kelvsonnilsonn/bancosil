package com.github.bancosil.command.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountCommand(
        @NotBlank @NotNull String username,
        @NotBlank @NotNull String password,
        @NotBlank @NotNull String email,
        @NotBlank @NotNull String cpf,
        @NotBlank @NotNull String type
) {
}
