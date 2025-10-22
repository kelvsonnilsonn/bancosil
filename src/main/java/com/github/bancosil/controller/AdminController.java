package com.github.bancosil.controller;

import com.github.bancosil.command.account.DeleteAccountCommand;
import com.github.bancosil.service.command.AccountCommandService;
import com.github.bancosil.service.query.AccountQueryService;
import com.github.bancosil.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.ADMIN_PATH)
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
@RequiredArgsConstructor
public class AdminController {

    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;

    @DeleteMapping(AppConstants.ACCOUNT_BASE_PATH)
    public ResponseEntity<Void> delete(@RequestBody @Valid DeleteAccountCommand command){
        accountCommandService.delete(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping(AppConstants.ACCOUNT_BASE_PATH)
    public ResponseEntity<?> findAccount(
            Pageable pageable,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username) {
        if (id != null) {
            return ResponseEntity.ok(accountQueryService.findById(id));
        }
        if(username != null){
            return ResponseEntity.ok(accountQueryService.findByUsername(username));
        }
        return ResponseEntity.ok(accountQueryService.findAll(pageable));
    }
}
