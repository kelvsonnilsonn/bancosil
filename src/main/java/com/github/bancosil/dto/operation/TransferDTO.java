package com.github.bancosil.dto.operation;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDTO(@NotNull BigDecimal amount, @NotNull Long id) {
}
