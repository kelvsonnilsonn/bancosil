package com.github.bancosil.exception.account;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Usuário não logado.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
