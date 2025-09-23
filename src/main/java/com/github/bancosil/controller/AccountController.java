package com.github.bancosil.controller;

import com.github.bancosil.config.AppConstants;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.dto.PageResponse;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import com.github.bancosil.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Por: Kelvson Nilson
 * Última atualização: 23/09/2025
 * Versão: 1.3
 * */

@RestController
@RequestMapping(AppConstants.ACCOUNT_BASE_PATH)
public class AccountController implements AccountAPI{

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping(AppConstants.CREATE_PATH)
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO user){
        Account account = new Corrente(user.username(), user.password(), user.email(), user.cpf());
        accountService.create(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.created(location).body(AccountConverter.convert(account));
    }

    @DeleteMapping(AppConstants.DELETE_PATH)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        String message = String.format(AppConstants.ACCOUNT_DELETED_MSG, account.getUsername());
        accountService.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        return ResponseEntity.ok(AccountConverter.convert(account));
    }

    @GetMapping(AppConstants.SEARCH_PATH)
    public ResponseEntity<PageResponse<AccountDTO>> findByUsername(
        @RequestParam(AppConstants.USERNAME_PARAM) String username,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));

        Page<Account> content = accountService.findByUsername(username, pageable);

        List<AccountDTO> accounts = content.getContent()
                .stream()
                .map(AccountConverter::convert)
                .toList();

        return accounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(createPageResponse(content, accounts));
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponse<AccountDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));

        Page<Account> content = accountService.findAll(pageable);

        List<AccountDTO> accounts = content.getContent()
                .stream()
                .map(AccountConverter::convert)
                .toList();

        return accounts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(createPageResponse(content, accounts));
    }

    private PageResponse<AccountDTO> createPageResponse(Page<Account> content, List<AccountDTO> accounts){
        return new PageResponse<>(
                accounts,
                content.getNumber(),
                content.getTotalPages(),
                content.getTotalElements(),
                content.getSize(),
                HttpStatus.OK.value()
        );
    }
}
