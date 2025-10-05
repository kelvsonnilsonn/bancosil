package com.github.bancosil.service;

import com.github.bancosil.dto.auth.AuthResponseDTO;
import com.github.bancosil.dto.auth.LoginRequestDTO;
import com.github.bancosil.dto.auth.RegisterRequestDTO;
import com.github.bancosil.enums.AccountType;
import com.github.bancosil.exception.AccountAlreadyExistsException;
import com.github.bancosil.exception.FailedLoginAttemptException;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.valueobjects.Email;
import com.github.bancosil.model.valueobjects.Password;
import com.github.bancosil.model.valueobjects.Username;
import com.github.bancosil.model.valueobjects.cpfchecker.CPF;
import com.github.bancosil.repository.AccountRepository;
import com.github.bancosil.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional(readOnly = true)
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto){
        Account account = accountRepository.findByUsername(dto.username()).orElseThrow(AccountNotFoundException::new);
        if(!(passwordEncoder.matches(dto.password(), account.getPassword()))){
            throw new FailedLoginAttemptException();
        }
        String token = tokenService.generateToken(account);
        return new AuthResponseDTO(token, account.getUsername());
    }

    public AuthResponseDTO register(@RequestBody RegisterRequestDTO dto){
        Optional<Account> account = accountRepository.findByUsername(dto.username());
        if(account.isPresent()){
            throw new AccountAlreadyExistsException();
        }
        Account newAccount = createAccount(dto);
        accountRepository.save(newAccount);
        String token = tokenService.generateToken(newAccount);
        return new AuthResponseDTO(token, newAccount.getUsername());
    }

    private Account createAccount(RegisterRequestDTO dto){
        Username username = new Username(dto.username());
        Password password = Password.of(dto.password(), passwordEncoder);
        CPF cpf = new CPF(dto.cpf());
        AccountType type = AccountType.valueOf(dto.type().toUpperCase());
        Email email = new Email(dto.email());
        return new Account(username, password, email, cpf, type);
    }
}
