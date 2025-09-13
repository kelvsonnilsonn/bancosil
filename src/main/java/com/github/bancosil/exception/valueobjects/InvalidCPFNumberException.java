package com.github.bancosil.exception.valueobjects;

public class InvalidCPFNumberException extends RuntimeException {
    public InvalidCPFNumberException() { super("O CPF não segue um padrão válido (XXX.XXX.XXX-xx ou XXXXXXXXXXX)"); }
    public InvalidCPFNumberException(String message) {
        super(message);
    }
}
