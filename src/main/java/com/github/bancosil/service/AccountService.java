package com.github.bancosil.service;

import com.github.bancosil.config.LoginSystem;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * Por: Kelvson Nilson
 * Última atualização: 23/09/2025
 * */

@Service
public class AccountService implements LoginSystem {

    private final AccountRepository accountDAO;

    @Autowired
    public AccountService(AccountRepository accountDAO){
        this.accountDAO = accountDAO;
    }

    public void create(Account account){
        Objects.requireNonNull(account, "A conta não pode ser nula.");
        accountDAO.save(account);
    }

    public void delete(Long id){
        try{
            accountDAO.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new AccountNotFoundException(id);
        }
    }

    public Account findById(Long id){
        Objects.requireNonNull(id, "O id não pode ser nulo.");
        return accountDAO.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Page<Account> findByUsername(String username, Pageable pageable){
        return accountDAO.findByUsername(username, pageable);
    }

    public Page<Account> findAll(Pageable pageable){
        return accountDAO.findAll(pageable);
    }

    @Override
    public Account login(String username, String password){
        return accountDAO.findByUsernameAndPassword(username, password)
                .orElseThrow(AccountNotFoundException::new);
    }

    public void update(Account ...account) {
        Arrays.stream(account).forEach(accountDAO::save);
    }

}
