package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.config.AppConstants;
import com.github.bancosil.exception.account.UnauthorizedException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.service.OperationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(AppConstants.OPERATION_BASE_PATH)
public class OperationController implements OperationAPI{

    private final OperationalService operationalService;
    private final AccountService accountService;
    private final AccountConfigurations accountConfigurations;

    @Autowired
    public OperationController(OperationalService operationalService,
                               AccountService accountService,
                               AccountConfigurations accountConfigurations){
        this.accountService = accountService;
        this.operationalService = operationalService;
        this.accountConfigurations = accountConfigurations;
    }

    @Override
    @PostMapping(AppConstants.DEPOSIT_PATH)
    public ResponseEntity<String> deposit(@RequestParam BigDecimal amount){
        Account account = getCurrentUser();
        operationalService.deposit(account, amount);
        return ResponseEntity.ok(AppConstants.DEPOSIT_MSG);
    }

    @Override
    @PostMapping(AppConstants.WITHDRAW_PATH)
    public ResponseEntity<String> withdraw(@RequestParam BigDecimal amount){
        Account account = getCurrentUser();
        operationalService.withdraw(account, amount);
        return ResponseEntity.ok(AppConstants.WITHDRAW_MSG);
    }

    @Override
    @PostMapping(AppConstants.TRANSFER_PATH)
    public ResponseEntity<String> transfer(@RequestParam Long id, @RequestParam BigDecimal amount){
        Account sender = getCurrentUser();
        Account receiver = accountService.findById(id);
        operationalService.transferPix(sender, receiver, amount);
        return ResponseEntity.ok(AppConstants.TRANSFER_MSG);
    }

    private Account getCurrentUser(){
        if(!accountConfigurations.isLogged()){
            throw new UnauthorizedException();
        }
        return accountConfigurations.getCurrentUser();
    }
}
