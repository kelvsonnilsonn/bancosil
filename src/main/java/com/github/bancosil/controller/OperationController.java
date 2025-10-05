package com.github.bancosil.controller;

import com.github.bancosil.dto.operation.DepositDTO;
import com.github.bancosil.dto.operation.TransferDTO;
import com.github.bancosil.dto.operation.WithdrawDTO;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.service.OperationalService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.OPERATION_BASE_PATH)
@RequiredArgsConstructor
public class OperationController implements OperationAPI{

    private final OperationalService operationalService;

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
        operationalService.transferPix(transferDTO);
        return ResponseEntity.ok(AppConstants.TRANSFER_MSG);
    }
}
