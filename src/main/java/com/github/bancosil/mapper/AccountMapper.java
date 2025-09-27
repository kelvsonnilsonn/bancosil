package com.github.bancosil.mapper;

import com.github.bancosil.dto.AccountRequestDTO;
import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;

public class AccountMapper {
    public static Account convert(AccountRequestDTO account){
        return new Corrente(account.username(), account.password(), account.email(), account.cpf());
    }

    public static AccountResponseDTO toResponse(Account account){
        return new AccountResponseDTO(account.getId(), account.getUsername(), account.getEmail(), account.getCreatedAt());
    }
}
