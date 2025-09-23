package com.github.bancosil.dto;

import java.math.BigDecimal;

public record TransferDTO(BigDecimal amount, Long id) {
}
