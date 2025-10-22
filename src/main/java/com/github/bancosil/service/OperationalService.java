package com.github.bancosil.service;

import com.github.bancosil.command.operation.TransferCommand;
import com.github.bancosil.event.Operation.DepositEvent;
import com.github.bancosil.event.Operation.TransferEvent;
import com.github.bancosil.event.Operation.WithdrawEvent;
import com.github.bancosil.exception.operational.SelfTransferException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.command.AccountCommandService;
import com.github.bancosil.service.operation.*;
import com.github.bancosil.service.query.AccountQueryService;
import com.github.bancosil.util.AppConstants;
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
    private final EventStoreService eventStoreService;

    public void withdraw(BigDecimal amount){
        executeOperation(Withdraw::new, getAuthenticatedUser(), amount);
        WithdrawEvent event = new WithdrawEvent(getAuthenticatedUser().getId(), amount);
        eventStoreService.saveEvent(AppConstants.OPERATION, getAuthenticatedUser().getId(), event);
    }

    public void deposit(BigDecimal amount){
        executeOperation(Deposit::new,  getAuthenticatedUser(), amount);
        DepositEvent event = new DepositEvent(getAuthenticatedUser().getId(), amount);
        eventStoreService.saveEvent(AppConstants.OPERATION, getAuthenticatedUser().getId(), event);
    }

    public void transferPix(TransferCommand dto){
        Account receiver = accountQueryService.findEntityById(dto.id());
        Account sender = getAuthenticatedUser();
        if(Objects.equals(sender.getId(), receiver.getId())){
            throw new SelfTransferException();
        }
        executeTransfer(TransferPix::new, sender, receiver, dto.amount());
        TransferEvent event = new TransferEvent(sender.getId(), receiver.getId(), dto.amount());
        eventStoreService.saveEvent(AppConstants.OPERATION, sender.getId(), event);
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
