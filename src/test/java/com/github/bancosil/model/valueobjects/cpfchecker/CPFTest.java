package com.github.bancosil.model.valueobjects.cpfchecker;

import com.github.bancosil.exception.valueobjects.InvalidCPFNumberException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    void shouldAcceptValidCPFFormats() {
        assertDoesNotThrow(() -> new CPF("123.456.789-09"));
        assertDoesNotThrow(() -> new CPF("12345678909"));
        assertDoesNotThrow(() -> new CPF("111.444.777-35"));
    }

    @Test
    void shouldRejectInvalidCPFFormats() {
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("123")); // muito curto
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("123.456.789-09123")); // muito longo
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("abc.def.ghi-jk")); // com letras
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("111.111.111-11")); // dígitos iguais
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("000.000.000-00")); // zeros
    }

    @Test
    void shouldRejectNullCPF() {
        assertThrows(NullPointerException.class, () -> new CPF(null));
    }

    @Test
    void shouldRejectEmptyCPF() {
        assertThrows(InvalidCPFNumberException.class, () -> new CPF(""));
    }

    @Test
    void shouldRejectCPFWithSpecialCharacters() {
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("123@456#789-09"));
        assertThrows(InvalidCPFNumberException.class, () -> new CPF("123.456.789_09"));
    }

    @Test
    void shouldAcceptCPFWithMixedFormatting() {
        assertDoesNotThrow(() -> new CPF("123456.789-09"));
        assertDoesNotThrow(() -> new CPF("123.456789-09"));
    }
}