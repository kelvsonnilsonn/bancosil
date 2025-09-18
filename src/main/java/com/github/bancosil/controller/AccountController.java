package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountDTO;
import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import com.github.bancosil.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public String create(@RequestBody AccountDTO user){
        Account conta = new Corrente(user.username(), user.password(), user.email(), user.cpf());
        accountService.create(conta);
        return "Hi, " + conta.getUsername();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        accountService.delete(id);
        return ResponseEntity.ok("Account " + account.getUsername() + " was deleted");
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") Long id){
        Account account = accountService.findById(id);
        return ResponseEntity.ok("Username: " + account.getUsername() + "\nEmail: " + account.getEmail());
    }
}
