package com.github.bancosil.service;

import com.github.bancosil.model.Account;
import com.github.bancosil.service.operation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Service
@Transactional
public class OperationalService {

    private final LogService logService;
    private final AccountService accountService;

    @Autowired
    public OperationalService(LogService logService, AccountService accountService){
        this.logService = logService;
        this.accountService = accountService;
    }

    public BigDecimal withdraw(Account account, BigDecimal amount){
        executeOperation(Withdraw::new, account, amount);
        return amount;
    }

    public void deposit(Account account, BigDecimal amount){
        executeOperation(Deposit::new, account, amount);
    }

    public void transferPix(Account sender, Account receiver, BigDecimal amount){
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
}
