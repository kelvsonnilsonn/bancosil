package com.github.bancosil.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.bancosil.exception.InvalidIntervalDateException;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DateIntervalDTO(
        @NotNull
        @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
        LocalDateTime startDate,
        @NotNull
        @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
        LocalDateTime endDate

) {
}
