package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ZipCode {
    private String zipcode;

    public ZipCode(String zipcode){
        this.zipcode = Objects.requireNonNull(zipcode);;
    }

    public static ZipCode of(String zipcode){
        return new ZipCode(zipcode);
    }
}
