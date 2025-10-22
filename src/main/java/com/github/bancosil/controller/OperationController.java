package com.github.bancosil.controller;

import com.github.bancosil.command.operation.DepositCommand;
import com.github.bancosil.command.operation.TransferCommand;
import com.github.bancosil.command.operation.WithdrawCommand;
import com.github.bancosil.service.OperationalService;
import com.github.bancosil.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.OPERATION_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class OperationController implements OperationAPI{

    private final OperationalService operationalService;

    @PostMapping(AppConstants.DEPOSIT_PATH)
    public ResponseEntity<String> deposit(@RequestBody @Valid DepositCommand depositCommand){
        operationalService.deposit(depositCommand.amount());
        return ResponseEntity.ok(AppConstants.DEPOSIT_MSG);
    }

    @Override
    @PostMapping(AppConstants.WITHDRAW_PATH)
    public ResponseEntity<String> withdraw(@RequestBody @Valid WithdrawCommand withdrawCommand){
        operationalService.withdraw(withdrawCommand.amount());
        return ResponseEntity.ok(AppConstants.WITHDRAW_MSG);
    }

    @Override
    @PostMapping(AppConstants.TRANSFER_PATH)
    public ResponseEntity<String> transfer(@RequestBody @Valid TransferCommand transferCommand){
        operationalService.transferPix(transferCommand);
        return ResponseEntity.ok(AppConstants.TRANSFER_MSG);
    }
}
