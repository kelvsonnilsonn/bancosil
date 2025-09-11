package com.github.bancosil.exception.operational;

public class NegativeOperationException extends RuntimeException {
    public NegativeOperationException() { super ("Foi passado um valor menor que 0 para o saque. Tente novamento com um valor maior que zero."); }
    public NegativeOperationException(String message) {
        super(message);
    }
}
