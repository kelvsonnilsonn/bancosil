package com.github.bancosil.controller;

import com.github.bancosil.util.AppConstants;
import com.github.bancosil.util.HttpConstants;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name="Accounts", description = "Gestão de contas de usuários")
public interface AccountAPI {

    @Operation(summary = "Criar nova conta", description = "Cria uma nova conta do usuário.")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Conta criada com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.CONFLICT, description = "Conflito - conta já existe")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AccountDTO> create(@RequestBody AccountDTO user);

    @Operation(summary = "Deletar uma conta", description = "Deleta uma conta de usuário.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta deletada com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> delete(@PathVariable("id") Long id);

    @Operation(summary = "Procurar uma conta por ID", description = "Retorna uma conta específica.")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta encontrada")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id);

    @Operation(summary = "Buscar por username", description = "Busca contas por username com paginação")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Contas encontradas")
    @ApiResponse(responseCode = HttpConstants.NO_CONTENT, description = HttpConstants.NO_CONTENT_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponse<AccountDTO>> findByUsername(
            @RequestParam(AppConstants.USERNAME_PARAM) String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Listar todas as contas", description = "Retorna todas as contas com paginação")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Lista de contas")
    @ApiResponse(responseCode = HttpConstants.NO_CONTENT, description = HttpConstants.NO_CONTENT_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponse<AccountDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);
}
