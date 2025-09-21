package com.github.bancosil.exception.operational;

public class SelfTransferException extends RuntimeException {
    public SelfTransferException(String message) {
        super(message);
    }
}
