package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
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
@RequestMapping("/operation")
public class OperationController {

    private final OperationalService operationalService;
    private final AccountService accountService;

    @Autowired
    public OperationController(OperationalService operationalService, AccountService accountService){
        this.accountService = accountService;
        this.operationalService = operationalService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam BigDecimal amount){
        Account account = getCurrentUser();
        operationalService.deposit(account, amount);
        return ResponseEntity.ok("Deposito de " + amount.toString() + " realizado com sucesso");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam BigDecimal amount){
        Account account = getCurrentUser();
        operationalService.withdraw(account, amount);
        return ResponseEntity.ok("Saque de " + amount.toString() + " realizado com sucesso");
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam Long id, @RequestParam BigDecimal amount){
        Account sender = getCurrentUser();
        Account receiver = accountService.findById(id);
        operationalService.transferPix(sender, receiver, amount);
        return ResponseEntity.ok("Transferência realizada com sucesso");
    }

    private Account getCurrentUser(){
        if(!AccountConfigurations.isLogged()){
            throw new UnauthorizedException();
        }
        return AccountConfigurations.getCurrentUser();
    }
}
