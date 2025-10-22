package com.github.bancosil.service.query;

import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.mapper.AccountMapper;
import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

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
}
