package com.github.bancosil.controller;

import com.github.bancosil.command.account.DeleteAccountCommand;
import com.github.bancosil.dto.EventIntervalDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.dto.UserEventsIntervalDTO;
import com.github.bancosil.event.EventStore;
import com.github.bancosil.service.EventStoreService;
import com.github.bancosil.service.command.AccountCommandService;
import com.github.bancosil.service.query.AccountQueryService;
import com.github.bancosil.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.ADMIN_PATH)
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
@RequiredArgsConstructor
public class AdminController implements AdminAPI{

    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;
    private final EventStoreService eventStoreService;

    @DeleteMapping(AppConstants.ACCOUNT_BASE_PATH)
    public ResponseEntity<Void> delete(@RequestBody @Valid DeleteAccountCommand command){
        accountCommandService.delete(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(AppConstants.ACCOUNT_BASE_PATH)
    public ResponseEntity<?> findAccount(
            Pageable pageable,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username) {
        if (id != null) {
            return ResponseEntity.ok(accountQueryService.findById(id));
        }
        if(username != null){
            return ResponseEntity.ok(accountQueryService.findByUsername(username));
        }
        return ResponseEntity.ok(accountQueryService.findAll(pageable));
    }

    // SEÇÃO DE EVENTOS

    @GetMapping(AppConstants.EVENT_BASE_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findEvents(Pageable pageable,
                                                             @RequestParam(required = false) Long userId,
                                                             @RequestParam(required = false) Long aggregateId,
                                                             @RequestParam(required = false) String aggregateType) {

        if(userId != null){
            return ResponseEntity.ok(eventStoreService.findAllByUserId(pageable, userId));
        }
        if(aggregateId != null){
            return ResponseEntity.ok(eventStoreService.findByAggregateId(pageable, aggregateId));
        }
        if(aggregateType != null){
            return ResponseEntity.ok(eventStoreService.findByAggregateType(pageable, aggregateType));
        }
        return ResponseEntity.ok(eventStoreService.findAllEvents(pageable));
    }

    @PostMapping(AppConstants.EVENT_BASE_PATH + AppConstants.EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findAllByInterval(pageable, intervalDTO));
    }

    @PostMapping(AppConstants.EVENT_BASE_PATH + AppConstants.USER_EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable, @RequestBody UserEventsIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findUserEventsByInterval(pageable, intervalDTO));
    }
}
