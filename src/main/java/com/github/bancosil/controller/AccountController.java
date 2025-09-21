package com.github.bancosil.controller;

import com.github.bancosil.config.AppConstants;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import com.github.bancosil.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<AccountDTO>> findByUsername(
            @RequestParam(AppConstants.USERNAME_PARAM) String username) {

        List<AccountDTO> accounts = accountService.findByUsername(username)
                .stream()
                .map(AccountConverter::convert)
                .toList();

        return accounts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(accounts);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accounts = accountService.findAll()
                .stream()
                .map(AccountConverter::convert)
                .toList();

        return accounts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(accounts);
    }
}
