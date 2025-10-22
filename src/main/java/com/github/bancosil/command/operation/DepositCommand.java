package com.github.bancosil.command.operation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositCommand(@NotNull @Positive BigDecimal amount) {
}
