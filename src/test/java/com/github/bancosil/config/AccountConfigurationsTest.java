package com.github.bancosil.config;

import com.github.bancosil.exception.account.AccountNotFoundException;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountConfigurationsTest {

    @Autowired
    private AccountConfigurations accountConfigurations;

    @Test
    void tentaLogarComSucesso() {
        assertDoesNotThrow(() -> accountConfigurations.login("Kelvson", "1234"));
    }

    @Test
    void logout() {
        accountConfigurations.logout();
        assertFalse(accountConfigurations.isLogged());
    }
}