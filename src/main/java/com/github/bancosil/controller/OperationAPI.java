package com.github.bancosil.controller;

import com.github.bancosil.util.HttpConstants;
import com.github.bancosil.dto.DepositDTO;
import com.github.bancosil.dto.TransferDTO;
import com.github.bancosil.dto.WithdrawDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Operations", description = "Operações que um usuário pode fazer.")
public interface OperationAPI {

    @Operation(summary = "Depósitar valor", description = "Realiza um depósito na conta do usuário logado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Depósito realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido, negativo ou zero")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO);

    @Operation(summary = "Sacar um valor", description = "Saca um valor da conta do usuário logado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Saque realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido, negativo, zero ou saldo insuficiente")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> withdraw(@RequestBody WithdrawDTO withdrawDTO);

    @Operation(summary = "Transferir um valor", description = "Transfere um valor da conta logada para outra conta")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Transferência realizada com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Valor inválido, negativo, zero, saldo insuficiente ou transferência para si mesmo")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Conta destino não encontrada")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO);
}
