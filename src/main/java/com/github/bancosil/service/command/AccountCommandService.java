package com.github.bancosil.service.command;

import com.github.bancosil.command.account.DeleteAccountCommand;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCommandService {

    private final AccountRepository accountRepository;

    public void delete(DeleteAccountCommand command){
        Account account = accountRepository.findById(command.id()).orElseThrow((AccountNotFoundException::new));
        accountRepository.delete(account);
    }

    public void update(Account ...account){
        Arrays.stream(account).forEach(accountRepository::save);
    }
}
