package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.ShortUsernameException;
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
        this.username = validate(username);
    }

    public static Username of(String username){
        return new Username(username);
    }

    private String validate(String username){
        Objects.requireNonNull(username, "O nome de usuário não pode ser nulo.");
        if(username.length() < 3){
            throw new ShortUsernameException();
        }
        return username;
    }
}
