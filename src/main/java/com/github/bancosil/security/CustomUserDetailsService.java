package com.github.bancosil.security;

import com.github.bancosil.model.Account;
import com.github.bancosil.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Conta não encontrada"));
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), new ArrayList<>());
    }
}
