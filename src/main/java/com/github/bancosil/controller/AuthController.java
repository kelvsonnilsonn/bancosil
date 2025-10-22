package com.github.bancosil.controller;

import com.github.bancosil.command.account.CreateAccountCommand;
import com.github.bancosil.dto.AuthResponseDTO;
import com.github.bancosil.command.auth.LoginCommand;
import com.github.bancosil.service.SecurityService;
import com.github.bancosil.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.AUTH_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class AuthController implements AuthAPI{

    private final SecurityService securityService;

    @Override
    @PostMapping(AppConstants.LOGIN_PATH)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginCommand loginCommand) {
        AuthResponseDTO response = securityService.login(loginCommand);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AppConstants.REGISTER_PATH)
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid CreateAccountCommand command){
        return ResponseEntity.ok(securityService.register(command));
    }

}