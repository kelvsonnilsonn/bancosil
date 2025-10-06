package com.github.bancosil.controller;

import com.github.bancosil.dto.DateIntervalDTO;
import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.service.LogService;
import com.github.bancosil.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.LOG_BASE_PATH)
@RequiredArgsConstructor
public class LogController implements LogAPI {

    private final LogService logService;

    @GetMapping(value={"", "/"})
    public ResponseEntity<PageResponseDTO<LogResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(logService.findAll(pageable));
    }

    @GetMapping(AppConstants.AUTHOR_LOG_PATH)
    public ResponseEntity<PageResponseDTO<LogResponseDTO>> findAuthorLogs(Pageable pageable, @RequestParam Long id){
        return ResponseEntity.ok(logService.findByAuthorId(pageable, id));
    }

    @GetMapping(AppConstants.INTERVAL_LOG_PATH)
    public ResponseEntity<PageResponseDTO<LogResponseDTO>> findByInterval(Pageable pageable, @RequestBody @Valid DateIntervalDTO dateInterval){
        return ResponseEntity.ok(logService.findByInterval(pageable, dateInterval.startDate(), dateInterval.endDate()));
    }
}
