package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.password.InvalidPasswordException;
import com.github.bancosil.exception.valueobjects.password.ShortPasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldAcceptValidPasswords() {
        assertDoesNotThrow(() -> new Password("StrongPass123!"));
        assertDoesNotThrow(() -> new Password("Another$123"));
    }

    @Test
    void shouldRejectShortPasswords() {
        assertThrows(ShortPasswordException.class, () -> new Password("short"));
    }

    @Test
    void shouldRejectInvalidPasswords() {
        assertThrows(InvalidPasswordException.class, () -> new Password("no special chars"));
    }

    @Test
    void shouldRejectNullPassword() {
        assertThrows(NullPointerException.class, () -> new Password(null));
    }
}