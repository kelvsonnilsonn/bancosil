package com.github.bancosil.service;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.exception.account.UnauthorizedException;
import com.github.bancosil.exception.operational.SelfTransferException;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.operation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Supplier;

@Service
@Transactional
public class OperationalService {

    private final LogService logService;
    private final AccountService accountService;
    private final AccountConfigurations accountConfigurations;


    public OperationalService(LogService logService, AccountService accountService, AccountConfigurations accountConfigurations){
        this.logService = logService;
        this.accountService = accountService;
        this.accountConfigurations = accountConfigurations;
    }

    public void withdraw(BigDecimal amount){
        executeOperation(Withdraw::new, getCurrentUser(), amount);
    }

    public void deposit(BigDecimal amount){
        executeOperation(Deposit::new,  getCurrentUser(), amount);
    }

    public void transferPix(Account receiver, BigDecimal amount){
        Account sender = getCurrentUser();
        if(Objects.equals(sender.getId(), receiver.getId())){
            throw new SelfTransferException("Você não pode transferir para si mesmo");
        }
        executeTransfer(TransferPix::new, sender, receiver, amount);
    }

    private void executeOperation(Supplier<Operation> operationSupplier, Account account, BigDecimal amount){
        Operation operation = operationSupplier.get();
        operation.execute(account, amount);
        accountService.update(account);
        logService.register(operation.operationType(), account, amount);
    }

    private void executeTransfer(Supplier<TransferOperation> operationSupplier, Account sender, Account receiver, BigDecimal amount){
        TransferOperation operation = operationSupplier.get();
        operation.execute(sender, receiver, amount);
        accountService.update(sender, receiver);
        logService.register(operation.operationType(), sender, receiver, amount);
    }

    private Account getCurrentUser(){
        if(!accountConfigurations.isLogged()){
            throw new UnauthorizedException();
        }
        return accountConfigurations.getCurrentUser();
    }
}
