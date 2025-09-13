package com.github.bancosil.exception.valueobjects;

public class ShortUsernameException extends RuntimeException {
    public ShortUsernameException() { super("O nome de usuário deve possuir mais de 3 letras."); }
    public ShortUsernameException(String message) {
        super(message);
    }
}
