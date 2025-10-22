package com.github.bancosil.controller;

import com.github.bancosil.command.account.CreateAccountCommand;
import com.github.bancosil.dto.AuthResponseDTO;
import com.github.bancosil.command.auth.LoginCommand;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name="Authentication", description = "Operações de autenticação de usuário")
public interface AuthAPI {

    @Operation(summary = "Login de usuário", description = "Autentica um usuário no sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Login realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = "Credenciais inválidas")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginCommand loginCommand);

    @Operation(summary = "Registrar usuário", description = "Registra um usuário no sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Registrado com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid CreateAccountCommand command);
}