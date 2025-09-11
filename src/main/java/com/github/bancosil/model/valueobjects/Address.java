package com.github.bancosil.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private ZipCode zipcode;
    private String street;
    private String city;
    private String country;
    private int number;

    public Address(String city, String street, String zipcode, int number){
        this(city, street, zipcode, number, "Brasil");
    }

    public Address(String city, String street, String zipcode, int number, String country){
        this.city = Objects.requireNonNull(city, "Cidade não pode ser nula");
        this.street = Objects.requireNonNull(street, "Rua não pode ser nula");
        this.zipcode = new ZipCode(Objects.requireNonNull(zipcode, "CEP não pode ser nulo"));
        this.number = validateNumber(number);
        this.country = Objects.requireNonNull(country, "País não pode ser nula");
    }

    public Address changeCountry(String country){
        return new Address(this.city, this.street, this.zipcode.getZipcode(), this.number, country);
    }

    public String getZipCode(){
        return zipcode.getZipcode();
    }

    private int validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Número deve ser positivo");
        }
        return number;
    }
}
