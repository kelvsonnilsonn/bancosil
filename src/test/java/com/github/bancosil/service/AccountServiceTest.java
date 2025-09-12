package com.github.bancosil.service;

import com.github.bancosil.exception.account.AccountNotFoundException;
import com.github.bancosil.model.Corrente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    void create() {
        service.create(new Corrente("Kelvson", "1234", "kelv@gmial.com", "72873847"));
    }

//    @Test
//    void deveEncontrarEDeletarOUsuarioLaDoBancoDeDados() {
//        assertDoesNotThrow(() -> service.delete(1L));
//    }

    @Test
    void deveEncontrarERetornarOUsuarioComOIDValidoLáDoBancoDeDados() {
        assertInstanceOf(Corrente.class, service.findById(2L));

    }
}