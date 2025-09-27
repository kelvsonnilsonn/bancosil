package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountRequestDTO;
import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.dto.PageResponseDTO;
import com.github.bancosil.service.AccountService;
import com.github.bancosil.util.AppConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.ACCOUNT_BASE_PATH)
public class AccountController implements AccountAPI{

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping(AppConstants.CREATE_PATH)
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO user){
        AccountResponseDTO account = accountService.create(user);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping(AppConstants.ID_PATH)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        String message = accountService.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable("id") Long id){
        AccountResponseDTO account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping(AppConstants.SEARCH_PATH)
    public ResponseEntity<PageResponseDTO<AccountResponseDTO>> findByUsername(
        @RequestParam(AppConstants.USERNAME_PARAM) String username,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));

        return ResponseEntity.ok(accountService.findByUsername(username, pageable));
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<AccountResponseDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
        return ResponseEntity.ok(accountService.findAll(pageable));
    }
}
