package com.github.bancosil.controller;

import com.github.bancosil.dto.EventIntervalDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.event.EventStore;
import com.github.bancosil.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Events", description = "API para consulta de eventos do sistema (Event Sourcing)")
public interface EventAPI {

    @Operation(summary = "Meus eventos", description = "Busca eventos relacionados ao usuário autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findMyEvents(Pageable pageable);

    @Operation(summary = "Meus eventos em intervalo", description = "Busca eventos do usuário autenticado dentro de um intervalo de datas")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findMyEventsInInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO);
}