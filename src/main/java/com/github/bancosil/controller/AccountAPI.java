package com.github.bancosil.controller;

import com.github.bancosil.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountAPI {
    ResponseEntity<AccountDTO> create(@RequestBody AccountDTO user);
    ResponseEntity<String> delete(@PathVariable("id") Long id);
    ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id);
    ResponseEntity<List<AccountDTO>> findByUsername(@RequestParam("username") String username);
    ResponseEntity<List<AccountDTO>> findAll();
}
