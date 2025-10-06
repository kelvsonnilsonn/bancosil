package com.github.bancosil.controller;

import com.github.bancosil.dto.DateIntervalDTO;
import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name="Logs", description = "Gestão de logs das operações")
public interface LogAPI {

    @Operation(summary = "Listar todos os logs", description = "Lista todos os logs dos sistema.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Logs listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<LogResponseDTO>> findAll(Pageable pageable);

    @Operation(summary = "Listar logs de usuário", description = "Lista todos os logs de um usuário.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Logs listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<LogResponseDTO>> findAuthorLogs(Pageable pageable, @RequestParam Long id);

    @Operation(summary = "Listar logs de usuário", description = "Lista todos os logs de um usuário.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Logs listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<LogResponseDTO>> findByInterval(Pageable pageable, @RequestBody @Valid DateIntervalDTO dateInterval);
}
