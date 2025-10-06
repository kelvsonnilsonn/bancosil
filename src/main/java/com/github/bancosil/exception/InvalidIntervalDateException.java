package com.github.bancosil.exception;

public class InvalidIntervalDateException extends RuntimeException {
    public InvalidIntervalDateException() { super("O intervalo de pesquisa é inválido. Data inicio é depois da data final"); }
    public InvalidIntervalDateException(String message) {
        super(message);
    }
}
