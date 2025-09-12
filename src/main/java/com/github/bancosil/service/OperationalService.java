package com.github.bancosil.service;

import com.github.bancosil.model.Account;
import com.github.bancosil.service.operation.Deposit;
import com.github.bancosil.service.operation.Operation;
import com.github.bancosil.service.operation.Withdraw;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Service
public class OperationalService {

    public BigDecimal withdraw(Account account, BigDecimal amount){
        executeOperation(Withdraw::new, account, amount);
        return amount;
    }

    public void deposit(Account account, BigDecimal amount){
        executeOperation(Deposit::new, account, amount);
    }

    private void executeOperation(Supplier<Operation> operationSupplier, Account account, BigDecimal amount){
        Operation operation = operationSupplier.get();
        operation.execute(account, amount);
    }
}
