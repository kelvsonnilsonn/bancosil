package com.github.bancosil.model.valueobjects;

import com.github.bancosil.exception.valueobjects.InvalidZipcodeNumberException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ZipCode {
    private String zipcode;

    private static final String ZIP_CODE_REGEX = "^\\d{5,}-?\\d{3,}$";
    private static final Pattern pattern = Pattern.compile(ZIP_CODE_REGEX);

    public ZipCode(String zipcode){
        this.zipcode = validate(zipcode);
    }

    public static ZipCode of(String zipcode){
        return new ZipCode(zipcode);
    }

    private String validate(String zipcode){
        Objects.requireNonNull(zipcode, "O CEP não pode ser nulo.");
        if(!pattern.matcher(zipcode).matches()){
            throw new InvalidZipcodeNumberException();
        }
        return zipcode;
    }
}
