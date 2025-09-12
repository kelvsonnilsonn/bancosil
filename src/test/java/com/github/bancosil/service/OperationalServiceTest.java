package com.github.bancosil.service;

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

    @Test
    void deveDepositarUmValorValido() {
        assertDoesNotThrow(() -> service.deposit(2L, BigDecimal.valueOf(120)));
    }

    @Test
    void deveRealizarUmSaqueComSucesso() {
        assertDoesNotThrow(() -> service.withdraw(2l, BigDecimal.valueOf(200)));
        assertEquals(0, BigDecimal.valueOf(60).compareTo(serviceAc.findById(2L).getMoney()));
    }

}