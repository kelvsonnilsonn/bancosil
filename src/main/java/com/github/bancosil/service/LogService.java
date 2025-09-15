package com.github.bancosil.service;

import com.github.bancosil.model.Account;
import com.github.bancosil.model.Log;
import com.github.bancosil.repository.LogRepository;
import com.github.bancosil.service.operation.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void register(OperationType operationType, Account account, BigDecimal amount) {
        Log log = new Log(operationType, account, amount);
        logRepository.save(log);
    }

    public void register(OperationType operationType, Account sender, Account receiver, BigDecimal amount) {
        Log log = new Log(operationType, sender, receiver, amount);
        logRepository.save(log);
    }
}