package com.github.bancosil.service;

import com.github.bancosil.config.LoginSystem;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public List<Account> findByUsername(String username){
        return accountDAO.findByUsername(username);
    }

    public List<Account> findAll(){
        return accountDAO.findAll();
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
