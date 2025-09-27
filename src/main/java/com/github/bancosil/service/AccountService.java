package com.github.bancosil.service;

import com.github.bancosil.config.LoginSystem;
import com.github.bancosil.dto.AccountRequestDTO;
import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.mapper.AccountMapper;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import com.github.bancosil.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Por: Kelvson Nilson
 * Última atualização: 23/09/2025
 * */

@Service
public class AccountService implements LoginSystem {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public AccountResponseDTO create(AccountRequestDTO account){
        Account receivedAccount = AccountMapper.convert(account);
        Account savedAccount = accountRepository.save(receivedAccount);
        return AccountMapper.toResponse(savedAccount);
    }

    public String delete(Long id){
        try{
            Account account = findEntityById(id);
            String message = String.format(AppConstants.ACCOUNT_DELETED_MSG, account.getUsername());
            accountRepository.deleteById(id);
            return message;
        } catch (EmptyResultDataAccessException e){
            throw new AccountNotFoundException(id);
        }
    }

    public Account findEntityById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public AccountResponseDTO findById(Long id){
        Account account = findEntityById(id);
        return AccountMapper.toResponse(account);
    }

    public PageResponseDTO<AccountResponseDTO> findByUsername(String username, Pageable pageable){
        Page<AccountResponseDTO> content =  accountRepository.findByUsername(username, pageable).map(AccountMapper::toResponse);
        return PageResponseDTO.fromPage(content);
    }

    public PageResponseDTO<AccountResponseDTO> findAll(Pageable pageable){
        Page<AccountResponseDTO> content = accountRepository.findAll(pageable).map(AccountMapper::toResponse);
        return PageResponseDTO.fromPage(content);
    }

    @Override
    public Account login(String username, String password){
        return accountRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(AccountNotFoundException::new);
    }

    public void update(Account ...account) {
        Arrays.stream(account).forEach(accountRepository::save);
    }

}
