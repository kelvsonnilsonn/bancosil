package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.config.AppConstants;
import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.AUTH_BASE_PATH)
public class AuthController implements AuthAPI{

    private final AccountConfigurations accountConfigurations;

    public AuthController(AccountConfigurations accountConfigurations) {
        this.accountConfigurations = accountConfigurations;
    }

    @Override
    @PostMapping(AppConstants.LOGIN_PATH)
    public ResponseEntity<AccountDTO> login(@RequestParam String username,
                                            @RequestParam String password) {
        accountConfigurations.login(username, password);
        Account account = accountConfigurations.getCurrentUser();
        return ResponseEntity.ok(AccountConverter.convert(account));
    }

    @Override
    @PostMapping(AppConstants.LOGOUT_PATH)
    public ResponseEntity<String> logout(){
        accountConfigurations.logout();
        return ResponseEntity.ok("Usuário deslogado.");
    }
}