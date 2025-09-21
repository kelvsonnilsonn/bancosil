package com.github.bancosil.controller;

import com.github.bancosil.config.AppConstants;
import com.github.bancosil.config.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@Tag(name = "Operations", description = "Operações que um usuário pode fazer.")
public interface OperationAPI {

    @Operation(summary= "Depósitar valor", description = "Realiza um depósito na conta do usuário logado")
    @ApiResponse(responseCode = HttpConstants.OK, description = AppConstants.DEPOSIT_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> deposit(@RequestParam BigDecimal amount);

    @Operation(summary = "Sacar um valor", description = "Saca um valor da conta do usuário logado")
    @ApiResponse(responseCode = HttpConstants.OK, description = AppConstants.WITHDRAW_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido ou saldo insuficiente")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> withdraw(@RequestParam BigDecimal amount);

    @Operation(summary = "Transferir um valor", description = "Transfere um valor da conta logada para outra conta")
    @ApiResponse(responseCode = HttpConstants.OK, description = AppConstants.TRANSFER_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido ou saldo insuficiente")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Conta destino não encontrada")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> transfer(@RequestParam Long id, @RequestParam BigDecimal amount);
}
