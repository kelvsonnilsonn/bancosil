package com.github.bancosil.service;

import com.github.bancosil.dto.LogResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.mapper.LogMapper;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Log;
import com.github.bancosil.repository.LogRepository;
import com.github.bancosil.service.operation.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;

    public void register(OperationType operationType, Account account, BigDecimal amount) {
        Log log = new Log(operationType, account, amount);
        logRepository.save(log);
    }

    public void register(OperationType operationType, Account sender, Account receiver, BigDecimal amount) {
        Log log = new Log(operationType, sender, receiver, amount);
        logRepository.save(log);
    }

    @Transactional(readOnly = true)
    public PageResponseDTO<LogResponseDTO> findAll(Pageable pageable){
        Page<LogResponseDTO> logs = logRepository.findAll(pageable).map(logMapper::toResponse);
        return PageResponseDTO.fromPage(logs);
    }
}