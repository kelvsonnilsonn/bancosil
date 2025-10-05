package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name="Contas", description = "Gestão de contas de usuários")
public interface AccountAPI {

    @Operation(summary = "Deletar uma conta", description = "Deleta uma conta de usuário.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta deletada com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> delete(@PathVariable Long id);

    @Operation(summary = "Procurar uma conta por ID", description = "Retorna uma conta específica.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta encontrada")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AccountResponseDTO> findById(@PathVariable Long id);

    @Operation(summary = "Buscar por username", description = "Busca contas por username com paginação")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Contas encontradas")
    @ApiResponse(responseCode = HttpConstants.NO_CONTENT, description = HttpConstants.NO_CONTENT_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AccountResponseDTO> findByUsername(@RequestParam String username);

    @Operation(summary = "Listar todas as contas", description = "Retorna todas as contas com paginação")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Lista de contas")
    @ApiResponse(responseCode = HttpConstants.NO_CONTENT, description = HttpConstants.NO_CONTENT_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<AccountResponseDTO>> findAll(Pageable pageable);
}
