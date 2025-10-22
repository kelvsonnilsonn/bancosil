package com.github.bancosil.exception.valueobjects.password;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() { super("Senha inserida não segue o padrão: ter mais de 6 caracteres, ao menos 1 letra maiúscula e ao menos 1 digito."); }
    public InvalidPasswordException(String message) {
        super(message);
    }
}
