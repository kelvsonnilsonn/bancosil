package com.github.bancosil.config;

import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;
import com.github.bancosil.exception.valueobjects.InvalidEmailException;
import com.github.bancosil.exception.valueobjects.ShortUsernameException;
import com.github.bancosil.exception.valueobjects.password.InvalidPasswordException;
import com.github.bancosil.exception.valueobjects.password.ShortPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPasswordException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ShortPasswordException.class)
    public ResponseEntity<String> handleShortPassword(ShortPasswordException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmail(InvalidEmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCPFNumberException.class)
    public ResponseEntity<String> handleInvalidCPF(InvalidCPFNumberException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ShortUsernameException.class)
    public ResponseEntity<String> handleShortUsername(ShortUsernameException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }
}
