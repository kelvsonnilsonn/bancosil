package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.config.AppConstants;
import com.github.bancosil.dto.DepositDTO;
import com.github.bancosil.dto.TransferDTO;
import com.github.bancosil.dto.WithdrawDTO;
import com.github.bancosil.exception.account.UnauthorizedException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.service.OperationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Por: Kelvson Nilson
 * Última atualização: 23/09/2025
 * Versão: 1.1
 * */

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
    public ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO){
        Account account = getCurrentUser();
        operationalService.deposit(account, depositDTO.amount());
        return ResponseEntity.ok(AppConstants.DEPOSIT_MSG);
    }

    @Override
    @PostMapping(AppConstants.WITHDRAW_PATH)
    public ResponseEntity<String> withdraw(@RequestBody WithdrawDTO withdrawDTO){
        Account account = getCurrentUser();
        operationalService.withdraw(account, withdrawDTO.amount());
        return ResponseEntity.ok(AppConstants.WITHDRAW_MSG);
    }

    @Override
    @PostMapping(AppConstants.TRANSFER_PATH)
    public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO){
        Account sender = getCurrentUser();
        Account receiver = accountService.findById(transferDTO.id());
        operationalService.transferPix(sender, receiver, transferDTO.amount());
        return ResponseEntity.ok(AppConstants.TRANSFER_MSG);
    }

    private Account getCurrentUser(){
        if(!accountConfigurations.isLogged()){
            throw new UnauthorizedException();
        }
        return accountConfigurations.getCurrentUser();
    }
}
