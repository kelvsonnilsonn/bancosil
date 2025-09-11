package com.github.bancosil.exception.operational;

public class ZeroAttemptException extends RuntimeException {
    public ZeroAttemptException() { super("Uma operação envolvendo o valor R$0.0 é ignorada."); }
    public ZeroAttemptException(String message) {
        super(message);
    }
}
