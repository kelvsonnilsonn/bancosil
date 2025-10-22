package com.github.bancosil.service;

import com.github.bancosil.command.operation.TransferCommand;
import com.github.bancosil.exception.operational.SelfTransferException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.command.AccountCommandService;
import com.github.bancosil.service.operation.*;
import com.github.bancosil.service.query.AccountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationalService {

    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;
    private final AuthenticationInformation authenticationInformation;

    public void withdraw(BigDecimal amount){
        executeOperation(Withdraw::new, getAuthenticatedUser(), amount);
    }

    public void deposit(BigDecimal amount){
        executeOperation(Deposit::new,  getAuthenticatedUser(), amount);
    }

    public void transferPix(TransferCommand dto){
        Account receiver = accountQueryService.findEntityById(dto.id());
        Account sender = getAuthenticatedUser();
        if(Objects.equals(sender.getId(), receiver.getId())){
            throw new SelfTransferException();
        }
        executeTransfer(TransferPix::new, sender, receiver, dto.amount());
    }

    private void executeOperation(Supplier<Operation> operationSupplier, Account account, BigDecimal amount){
        Operation operation = operationSupplier.get();
        operation.execute(account, amount);
        accountCommandService.update(account);
    }

    private void executeTransfer(Supplier<TransferOperation> operationSupplier, Account sender, Account receiver, BigDecimal amount){
        TransferOperation operation = operationSupplier.get();
        operation.execute(sender, receiver, amount);
        accountCommandService.update(sender, receiver);
    }

    private Account getAuthenticatedUser(){
        return authenticationInformation.getAuthenticatedUser();
    }
}
