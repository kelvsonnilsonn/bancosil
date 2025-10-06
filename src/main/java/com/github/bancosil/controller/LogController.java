package com.github.bancosil.controller;

import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.service.LogService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
