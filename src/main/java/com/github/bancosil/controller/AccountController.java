package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import com.github.bancosil.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public String create(@RequestBody AccountDTO user){
        Account account = new Corrente(user.username(), user.password(), user.email(), user.cpf());
        accountService.create(account);
        return "Hi, " + account.getUsername();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        accountService.delete(id);
        return ResponseEntity.ok("Account " + account.getUsername() + " was deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        return ResponseEntity.ok(convertDTO(account));
    }

    private AccountDTO convertDTO(Account account){
        return new AccountDTO(
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                account.getCPF());
    }
}
