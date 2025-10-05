package com.github.bancosil.dto;

import com.github.bancosil.service.operation.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LogResponseDTO(
        Long id,
        String author,
        String receiver,
        OperationType operationType,
        BigDecimal amount,
        LocalDateTime createdAt
) {
}
