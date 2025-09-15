package com.github.bancosil.model.valueobjects.zipcodechecker;

import com.github.bancosil.exception.valueobjects.InvalidZipcodeNumberException;

import java.util.Objects;
import java.util.regex.Pattern;

 class ZipcodeFormatterValidate {

    private static final String ZIP_CODE_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern pattern = Pattern.compile(ZIP_CODE_REGEX);

    protected static final String UNDEFINED = "Não definida";

    protected static String validate(String zipcode){
        Objects.requireNonNull(zipcode, "O CEP não pode ser nulo.");

        if(zipcode.equals(UNDEFINED)){
            return zipcode;
        }

        String cleanedZipcode = zipcode.trim();

        if(!pattern.matcher(cleanedZipcode).matches()){
            throw new InvalidZipcodeNumberException();
        }

        return cleanedZipcode;
    }
}
