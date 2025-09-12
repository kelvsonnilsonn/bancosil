package com.github.bancosil.service;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OperationalServiceTest {

    @Autowired
    private OperationalService service;

    @Autowired
    private AccountService serviceAc;

    @Autowired
    private AccountConfigurations accountConfigurations;

    @Test
    void deveDepositarUmValorValido() {
        assertDoesNotThrow(() -> accountConfigurations.login("Kelvson", "1234"));
        Account conta = accountConfigurations.getCurrentUser();
        assertDoesNotThrow(() -> service.deposit(conta, BigDecimal.valueOf(100)));
        accountConfigurations.logout();
        serviceAc.update(conta);
    }

    @Test
    void deveEstarDeslogado(){
        assertFalse(accountConfigurations.isLogged());
    }

    @Test
    void valorDeveEstarGuardadoNoBancoDeDados(){
        Account conta = serviceAc.findById(2L);
        assertEquals(0, BigDecimal.valueOf(160).compareTo(conta.getMoney()));
    }

    @Test
    void deveSacarDinheiro(){
        Account conta = serviceAc.findById(2L);
        BigDecimal valor = service.withdraw(conta, BigDecimal.valueOf(100));
        assertEquals(0, BigDecimal.valueOf(100)
                .compareTo(valor));
        serviceAc.update(conta);
    }
}