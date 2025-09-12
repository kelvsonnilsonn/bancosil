package com.github.bancosil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("investmento")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Investimento extends Account {
}
