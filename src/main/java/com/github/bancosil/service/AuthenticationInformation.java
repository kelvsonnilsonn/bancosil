package com.github.bancosil.service;

import com.github.bancosil.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationInformation {

    public Account getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !(authentication.isAuthenticated())){
            throw new RuntimeException();
        }

        return (Account) authentication.getPrincipal();
    }

}
