package com.github.bancosil.command.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginCommand(@NotBlank @NotNull String username, @NotBlank @NotNull String password) {
}
