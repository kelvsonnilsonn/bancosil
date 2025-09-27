package com.github.bancosil.controller;

import com.github.bancosil.util.HttpConstants;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.dto.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name="Authentication", description = "Operações de autenticação de usuário")
public interface AuthAPI {

    @Operation(summary = "Login de usuário", description = "Autentica um usuário no sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Login realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = "Credenciais inválidas")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<AccountDTO> login(@RequestBody LoginDTO loginDTO);

    @Operation(summary = "Logout de usuário", description = "Desconecta um usuário do sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Logout realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> logout();
}