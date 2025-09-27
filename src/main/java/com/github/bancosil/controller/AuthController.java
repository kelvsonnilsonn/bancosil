package com.github.bancosil.controller;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.dto.AccountResponseDTO;
import com.github.bancosil.mapper.AccountMapper;
import com.github.bancosil.util.AppConstants;
import com.github.bancosil.dto.AccountRequestDTO;
import com.github.bancosil.dto.LoginDTO;
import com.github.bancosil.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<AccountResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Account account = accountConfigurations.login(loginDTO.username(), loginDTO.password());
        return ResponseEntity.ok(AccountMapper.toResponse(account));
    }

    @Override
    @PostMapping(AppConstants.LOGOUT_PATH)
    public ResponseEntity<String> logout(){
        accountConfigurations.logout();
        return ResponseEntity.ok("Usuário deslogado.");
    }
}