package com.github.bancosil.exception.operational;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(){ super("Você não possui saldo suficiente."); }
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
