package com.github.bancosil.exception.account;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Usuário não autorizado.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
