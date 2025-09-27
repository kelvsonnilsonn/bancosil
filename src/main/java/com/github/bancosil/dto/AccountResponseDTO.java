package com.github.bancosil.dto;

import java.time.LocalDateTime;

public record AccountResponseDTO(Long id, String username, String email, LocalDateTime createdAt) {
}
