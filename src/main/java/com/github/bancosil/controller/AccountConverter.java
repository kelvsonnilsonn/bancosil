package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;

class AccountConverter {
    public static AccountDTO convert(Account account){
        return new AccountDTO(
                account.getUsername(),
                account.getEmail(),
                null,
                account.getCPF()
        );
    }
}
