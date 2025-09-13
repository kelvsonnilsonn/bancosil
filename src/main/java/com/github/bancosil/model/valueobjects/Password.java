package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.password.InvalidPasswordException;
import com.github.bancosil.exception.valueobjects.password.ShortPasswordException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private String password;
    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[A-Z])[a-zA-Z\\d]+$";
            // Lookaheads (?=.*[algo]) não consomem caracteres. Eles só verificam uma condição!

    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    public Password(String password){
        this.password = validate(password);
    }

    public static Password of(String password){
        return new Password(password);
    }

    private String validate(String password){
        Objects.requireNonNull(password);
        if(password.length() < 6){
            throw new ShortPasswordException();
        }
        if(!pattern.matcher(password).matches()){
            throw new InvalidPasswordException();
        }
        return password;
    }
}
