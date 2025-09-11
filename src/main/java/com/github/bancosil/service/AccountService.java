package com.github.bancosil.service;

import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountDAO;

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

}
