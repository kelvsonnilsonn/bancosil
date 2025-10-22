package com.github.bancosil.exception.operational;

public class SelfTransferException extends RuntimeException {
    public SelfTransferException() { super("Você não pode transferir para si mesmo");}
    public SelfTransferException(String message) {
        super(message);
    }
}
