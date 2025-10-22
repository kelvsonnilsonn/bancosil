package com.github.bancosil.handler;

import com.github.bancosil.exception.AccountAlreadyExistsException;
import com.github.bancosil.exception.FailedLoginAttemptException;
import com.github.bancosil.exception.InvalidIntervalDateException;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.exception.account.UnauthorizedException;
import com.github.bancosil.exception.operational.NegativeOperationException;
import com.github.bancosil.exception.operational.SelfTransferException;
import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;
import com.github.bancosil.exception.valueobjects.InvalidEmailException;
import com.github.bancosil.exception.valueobjects.ShortUsernameException;
import com.github.bancosil.exception.valueobjects.password.InvalidPasswordException;
import com.github.bancosil.exception.valueobjects.password.ShortPasswordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(InvalidIntervalDateException.class)
    public ResponseEntity<String> handleInvalidDate(InvalidIntervalDateException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException e) {

        String message = e.getMessage().toLowerCase();

        if (message.contains("constraint_index_e4") && message.contains("username")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já está em uso");
        }

        if (message.contains("constraint_index_e") && message.contains("cpf")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já está em uso");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Dados duplicados");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Um erro inesperado aconteceu: " + e.getMessage());
    }

    @ExceptionHandler(NegativeOperationException.class)
    public ResponseEntity<String> handleNegativeException(NegativeOperationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Não tem como transferir um valor negativo.");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }

    @ExceptionHandler(SelfTransferException.class)
    public ResponseEntity<String> handleOperationException(SelfTransferException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountExistsException(AccountAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(FailedLoginAttemptException.class)
    public ResponseEntity<String> handleFailedLoginAttemptException(FailedLoginAttemptException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidIntervalDateException.class)
    public ResponseEntity<String> handleOperationException(InvalidIntervalDateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
