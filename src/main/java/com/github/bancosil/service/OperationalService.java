package com.github.bancosil.service;

import com.github.bancosil.model.Account;
import com.github.bancosil.service.validator.OperationValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Serviço onde ocorre as transferências, saques, depósitos, bla, bla e bla.
 * */

@Service
@Transactional
public class OperationalService {

    private final AccountService accountService;

    public OperationalService(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    public BigDecimal withdraw(Long id, BigDecimal amount){
        Account account = accountService.findById(id);
        OperationValidator.validate(amount);
        return account.withdraw(amount);
    }

    public void deposit(Long id, BigDecimal amount){
        Account account = accountService.findById(id);
        OperationValidator.validate(amount);
        account.deposit(amount);
    }
}
