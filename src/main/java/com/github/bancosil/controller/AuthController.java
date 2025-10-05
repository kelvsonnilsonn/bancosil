package com.github.bancosil.controller;

import com.github.bancosil.dto.auth.AuthResponseDTO;
import com.github.bancosil.dto.auth.LoginRequestDTO;
import com.github.bancosil.dto.auth.RegisterRequestDTO;
import com.github.bancosil.service.SecurityService;
import com.github.bancosil.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.AUTH_BASE_PATH)
@RequiredArgsConstructor
public class AuthController implements AuthAPI{

    private final SecurityService securityService;

    @Override
    @PostMapping(AppConstants.LOGIN_PATH)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        AuthResponseDTO response = securityService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping(AppConstants.REGISTER_PATH)
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        AuthResponseDTO response = securityService.register(registerRequestDTO);
        return ResponseEntity.ok(response);
    }

}