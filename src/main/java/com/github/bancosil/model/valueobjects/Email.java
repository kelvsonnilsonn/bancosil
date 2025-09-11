package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    private String email;

    public Email(String email){
        Objects.requireNonNull(email);
        this.email = email;
    }

    public Email of(String email){
        return new Email(email);
    }
}
