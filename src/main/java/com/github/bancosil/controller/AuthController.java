package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;
import com.github.bancosil.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountConfigurations accountConfigurations;

    public AuthController(AccountConfigurations accountConfigurations) {
        this.accountConfigurations = accountConfigurations;
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestParam String username,
                                            @RequestParam String password) {
        try {
            accountConfigurations.login(username, password);
            Account account = AccountConfigurations.getCurrentUser();
            return ResponseEntity.ok(AccountConverter.convert(account));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        accountConfigurations.logout();
        return ResponseEntity.ok("Usuário deslogado.");
    }
}