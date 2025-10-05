package com.github.bancosil.controller;

import com.github.bancosil.dto.operation.DepositDTO;
import com.github.bancosil.dto.operation.TransferDTO;
import com.github.bancosil.dto.operation.WithdrawDTO;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.service.OperationalService;
import com.github.bancosil.util.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(AppConstants.OPERATION_BASE_PATH)
public class OperationController implements OperationAPI{

    private final OperationalService operationalService;
    private final AccountService accountService;

    public OperationController(OperationalService operationalService,
                               AccountService accountService){
        this.accountService = accountService;
        this.operationalService = operationalService;
    }

    @PostMapping(AppConstants.DEPOSIT_PATH)
    public ResponseEntity<String> deposit(@RequestBody DepositDTO depositDTO){
        operationalService.deposit(depositDTO.amount());
        return ResponseEntity.ok(AppConstants.DEPOSIT_MSG);
    }

    @Override
    @PostMapping(AppConstants.WITHDRAW_PATH)
    public ResponseEntity<String> withdraw(@RequestBody WithdrawDTO withdrawDTO){
        operationalService.withdraw(withdrawDTO.amount());
        return ResponseEntity.ok(AppConstants.WITHDRAW_MSG);
    }

    @Override
    @PostMapping(AppConstants.TRANSFER_PATH)
    public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO){
        Account receiver = accountService.findEntityById(transferDTO.id());
        operationalService.transferPix(receiver, transferDTO.amount());
        return ResponseEntity.ok(AppConstants.TRANSFER_MSG);
    }
}
