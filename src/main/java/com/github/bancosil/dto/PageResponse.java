package com.github.bancosil.dto;

import java.util.List;

public record PageResponse<T> (
        List<T> content,
        int actualPage,
        int totalPages,
        long totalItems,
        int itemsShowedInPage,
        int httpStatus
) {
}
