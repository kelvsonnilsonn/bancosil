package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    void shouldAcceptValidEmails() {
        assertDoesNotThrow(() -> new Email("user@example.com"));
        assertDoesNotThrow(() -> new Email("first.last@example.com"));
        assertDoesNotThrow(() -> new Email("user123@example.com.br"));
    }

    @Test
    void shouldRejectInvalidEmails() {
        assertThrows(InvalidEmailException.class, () -> new Email("invalid"));
        assertThrows(InvalidEmailException.class, () -> new Email("user@"));
        assertThrows(InvalidEmailException.class, () -> new Email("@example.com"));
    }

    @Test
    void shouldRejectNullEmail() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }
}