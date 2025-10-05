package com.github.bancosil.service;

import com.github.bancosil.dto.operation.TransferDTO;
import com.github.bancosil.exception.operational.NegativeOperationException;
import com.github.bancosil.exception.operational.SelfTransferException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.operation.*;
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

    private final LogService logService;
    private final AccountService accountService;
    private final AuthenticationInformation authenticationInformation;

    public void withdraw(BigDecimal amount){
        executeOperation(Withdraw::new, getAuthenticatedUser(), amount);
    }

    public void deposit(BigDecimal amount){
        executeOperation(Deposit::new,  getAuthenticatedUser(), amount);
    }

    public void transferPix(TransferDTO dto){
        Account receiver = accountService.findEntityById(dto.id());
        Account sender = getAuthenticatedUser();
        if(Objects.equals(sender.getId(), receiver.getId())){
            throw new SelfTransferException("Você não pode transferir para si mesmo");
        }
        executeTransfer(TransferPix::new, sender, receiver, dto.amount());
    }

    private void executeOperation(Supplier<Operation> operationSupplier, Account account, BigDecimal amount){
        validate(amount);
        Operation operation = operationSupplier.get();
        operation.execute(account, amount);
        accountService.update(account);
        logService.register(operation.operationType(), account, amount);
    }

    private void executeTransfer(Supplier<TransferOperation> operationSupplier, Account sender, Account receiver, BigDecimal amount){
        validate(amount);
        TransferOperation operation = operationSupplier.get();
        operation.execute(sender, receiver, amount);
        accountService.update(sender, receiver);
        logService.register(operation.operationType(), sender, receiver, amount);
    }

    private Account getAuthenticatedUser(){
        return authenticationInformation.getAuthenticatedUser();
    }

    private void validate(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeOperationException();
        }
    }
}
