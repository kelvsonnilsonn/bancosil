package com.github.bancosil.model.valueobjects.zipcodechecker;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.github.bancosil.model.valueobjects.zipcodechecker.ZipcodeFormatterValidate.UNDEFINED;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ZipCode {
    private String zipcode;

    public ZipCode(String zipcode){
        this.zipcode = ZipcodeFormatterValidate.validate(zipcode);
    }

    public static ZipCode of(String zipcode){
        return new ZipCode(zipcode);
    }

    public boolean isDefined() {
        return !UNDEFINED.equals(zipcode);
    }

    public String getFormattedZipcode() {
        if (!isDefined()) {
            return UNDEFINED;
        }

        if (zipcode.contains("-")) {
            return zipcode;
        }
        return zipcode.substring(0, 5) + "-" + zipcode.substring(5);
    }

    @Override
    public String toString() {
        return getFormattedZipcode();
    }
}