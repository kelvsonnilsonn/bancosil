package com.github.bancosil.mapper;

import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;

public class AccountMapper {
    public static AccountDTO convert(Account account){
        return new AccountDTO(
                account.getUsername(),
                account.getEmail(),
                null,
                account.getCPF()
        );
    }
}
