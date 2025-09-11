package com.github.bancosil.service.validator;

import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.repository.AccountRepository;


public class AccountValidator {

    public static void validateExists(AccountRepository repository, Long id){
        if(!repository.existsById(id)){
            throw new AccountNotFoundException("Conta com ID: " + id + " não encontrada.");
        }
    }
}
