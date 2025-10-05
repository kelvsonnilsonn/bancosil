package com.github.bancosil.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() { super("A conta já existe"); }
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
