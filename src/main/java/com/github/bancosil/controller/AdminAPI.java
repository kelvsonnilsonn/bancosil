package com.github.bancosil.controller;

import com.github.bancosil.command.account.DeleteAccountCommand;
import com.github.bancosil.dto.EventIntervalDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.dto.UserEventsIntervalDTO;
import com.github.bancosil.event.EventStore;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminAPI {

    // SEÇÃO DE CONTAS

    @Operation(summary = "Excluir conta", description = "Exclui uma conta permanentemente do sistema (apenas administradores)")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta excluída com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Dados de exclusão inválidos")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Conta não encontrada")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> delete(@RequestBody @Valid DeleteAccountCommand command);

    @Operation(summary = "Buscar contas", description = """
        Busca contas por diferentes critérios.
        Prioridade: id > username > todos.
        Exemplos:
        - ?id=123 → Conta específica por ID
        - ?username=joao.silva → Conta por nome de usuário
        - Sem parâmetros → Todas contas (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Conta(s) retornada(s) com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Parâmetros de busca inválidos")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Conta não encontrada")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<?> findAccount(Pageable pageable,
                                  @RequestParam(required = false) Long id,
                                  @RequestParam(required = false) String username);

    // SEÇÃO DE EVENTOS

    @Operation(summary = "Buscar eventos", description = """
        Busca eventos por diferentes critérios.
        Prioridade: userId > aggregateId > aggregateType > todos.
        Exemplos:
        - ?userId=456 → Eventos do usuário
        - ?aggregateId=789 → Eventos do agregado
        - ?aggregateType=ACCOUNT → Eventos por tipo
        - Sem parâmetros → Todos eventos (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Parâmetros de filtro inválidos")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findEvents(Pageable pageable,
                                                           @RequestParam(required = false) Long userId,
                                                           @RequestParam(required = false) Long aggregateId,
                                                           @RequestParam(required = false) String aggregateType);

    @Operation(summary = "Buscar eventos em intervalo", description = "Busca todos os eventos em um intervalo de datas específico")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos do intervalo retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Intervalo de datas inválido")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable,
                                                                     @RequestBody @Valid EventIntervalDTO intervalDTO);

    @Operation(summary = "Buscar eventos de usuário em intervalo", description = "Busca eventos de um usuário específico em intervalo de datas")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos do usuário no intervalo retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Intervalo de datas inválido")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable,
                                                                            @RequestBody @Valid UserEventsIntervalDTO intervalDTO);
}