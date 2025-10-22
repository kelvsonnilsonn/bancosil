package com.github.bancosil.controller;

import com.github.bancosil.dto.EventIntervalDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.event.EventStore;
import com.github.bancosil.service.EventStoreService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.EVENT_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class EventController implements EventAPI {

    private final EventStoreService eventStoreService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEvents(Pageable pageable) {
        return ResponseEntity.ok(eventStoreService.findMyEvents(pageable));
    }

    @PostMapping(AppConstants.MY_EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEventsInInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findMyEventsByInterval(pageable, intervalDTO));
    }
}