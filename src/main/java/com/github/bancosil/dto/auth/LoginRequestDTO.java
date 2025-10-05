package com.github.bancosil.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(@NotBlank @NotNull String username, @NotBlank @NotNull String password) {
}
