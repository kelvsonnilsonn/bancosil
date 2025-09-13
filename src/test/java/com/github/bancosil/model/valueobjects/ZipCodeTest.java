package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.InvalidZipcodeNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZipCodeTest {

    @Test
    void shouldAcceptValidZipCodes() {
        assertDoesNotThrow(() -> new ZipCode("12345678"));
        assertDoesNotThrow(() -> new ZipCode("12345-678"));
    }

    @Test
    void shouldRejectInvalidZipCodes() {
        assertThrows(InvalidZipcodeNumberException.class, () -> new ZipCode("1234567")); // muito curto
        assertThrows(InvalidZipcodeNumberException.class, () -> new ZipCode("123456789")); // muito longo
        assertThrows(InvalidZipcodeNumberException.class, () -> new ZipCode("abc12345")); // com letras
    }

    @Test
    void shouldRejectNullZipCode() {
        assertThrows(NullPointerException.class, () -> new ZipCode(null));
    }
}