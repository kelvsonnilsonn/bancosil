package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Username {
    private String username;

    public Username(String username){
        Objects.requireNonNull(username);
        this.username = username;
    }

    public Username of(String username){
        return new Username(username);
    }
}
