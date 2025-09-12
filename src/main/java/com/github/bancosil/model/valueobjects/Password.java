package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private String password;

    public Password(String password){
        this.password = Objects.requireNonNull(password);;
    }

    public static Password of(String password){
        return new Password(password);
    }
}
