package com.github.bancosil.service;

import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.mapper.AccountMapper;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public String delete(Long id){
        Account account = findEntityById(id);
        String message = String.format(AppConstants.ACCOUNT_DELETED_MSG, account.getUsername());
        accountRepository.deleteById(id);
        return message;
    }

    public AccountResponseDTO findByUsername(String username){
        Account account = accountRepository.findByUsername(username).orElseThrow(AccountNotFoundException::new);
        return accountMapper.toResponse(account);
    }

    public AccountResponseDTO findById(Long id){
        Account account = findEntityById(id);
        return accountMapper.toResponse(account);
    }

    public PageResponseDTO<AccountResponseDTO> findAll(Pageable pageable){
        Page<AccountResponseDTO> content = accountRepository.findAll(pageable).map(accountMapper::toResponse);
        return PageResponseDTO.fromPage(content);
    }

    public Account findEntityById(Long id){
        return accountRepository.findById(id).orElseThrow((AccountNotFoundException::new));
    }

    @Transactional
    public void update(Account ...account){
        Arrays.stream(account).forEach(accountRepository::save);
    }
}
