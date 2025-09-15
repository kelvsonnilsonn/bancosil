package com.github.bancosil.service;

import com.github.bancosil.config.AccountConfigurations;
import com.github.bancosil.exception.operational.InsufficientBalanceException;
import com.github.bancosil.model.Account;
import com.github.bancosil.model.Corrente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        assertDoesNotThrow(() -> accountConfigurations.login("Kelvson", "Kel851762"));
        Account conta = accountConfigurations.getCurrentUser();
        assertDoesNotThrow(() -> service.deposit(conta, BigDecimal.valueOf(100)));
        accountConfigurations.logout();
    }
//
//    @Test
//    void deveEstarDeslogado(){
//        assertFalse(accountConfigurations.isLogged());
//    }

//    @Test
//    void deveSacarDinheiro(){
//        Account conta = serviceAc.findById(1L);
//        BigDecimal valor = service.withdraw(conta, BigDecimal.valueOf(100));
//        assertEquals(0, BigDecimal.valueOf(100)
//                .compareTo(valor));
//        serviceAc.update(conta);
//    }
//
    @Test
    void tentandoFazerUmaTransferenciaComSucesso(){
        Account sender = serviceAc.findById(1L);
        Account receiver = serviceAc.findById(2L);
        assertDoesNotThrow(() -> service.transferPix(receiver, sender, BigDecimal.valueOf(50)));
    }
}