package com.github.bancosil.exception.account;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id){ super("Conta com ID: " + id + " não encontrada.");}
    public AccountNotFoundException(String message) {
        super(message);
    }
}
