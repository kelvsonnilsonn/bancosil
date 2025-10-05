package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.util.AppConstants;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.ACCOUNT_BASE_PATH)
public class AccountController implements AccountAPI{
 
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @DeleteMapping(AppConstants.ID_PATH)
    public ResponseEntity<String> delete(@PathVariable Long id){
        String message = accountService.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable Long id){
        AccountResponseDTO account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping(AppConstants.SEARCH_PATH)
    public ResponseEntity<AccountResponseDTO> findByUsername(@RequestParam String username) {
        AccountResponseDTO account = accountService.findByUsername(username);
        return ResponseEntity.ok(account);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<AccountResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(accountService.findAll(pageable));
    }
}
