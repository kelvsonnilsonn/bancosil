package com.github.bancosil.controller;

import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name="Logs", description = "Gestão de logs das operações")
public interface LogAPI {

    @Operation(summary = "Listar todos os logs", description = "Lista todos os logs dos sistema.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Logs listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<LogResponseDTO>> findAll(Pageable pageable);
}
