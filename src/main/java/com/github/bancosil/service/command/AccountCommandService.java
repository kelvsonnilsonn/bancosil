package com.github.bancosil.service.command;

import com.github.bancosil.command.account.DeleteAccountCommand;
import com.github.bancosil.event.account.DeleteAccountEvent;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import com.github.bancosil.service.AuthenticationInformation;
import com.github.bancosil.service.EventStoreService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountCommandService {

    private final AccountRepository accountRepository;
    private final EventStoreService eventStoreService;
    private final AuthenticationInformation authenticationInformation;

    public void delete(DeleteAccountCommand command){
        Account account = accountRepository.findById(command.id()).orElseThrow((AccountNotFoundException::new));
        accountRepository.delete(account);
        DeleteAccountEvent event = new DeleteAccountEvent(account.getId(), command.reason(), authenticationInformation.getAuthenticatedUser().getId());
        eventStoreService.saveEvent(AppConstants.ACCOUNT, authenticationInformation.getAuthenticatedUser().getId(), event);
    }

    public void update(Account ...account){
        Arrays.stream(account).forEach(accountRepository::save);
    }
}
