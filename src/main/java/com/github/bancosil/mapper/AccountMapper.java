package com.github.bancosil.mapper;

import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "username", expression = "java(account.getUsername())")
    @Mapping(target = "email", expression = "java(account.getEmail())")
    AccountResponseDTO toResponse(Account account);
}
