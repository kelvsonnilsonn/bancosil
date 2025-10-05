package com.github.bancosil.controller;

import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController implements LogAPI {

    private final LogService logService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<LogResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(logService.findAll(pageable));
    }
}
