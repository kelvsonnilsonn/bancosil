package com.github.bancosil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("investmento")
public class Investimento extends Account {
}
