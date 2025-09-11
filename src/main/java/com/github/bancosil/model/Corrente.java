package com.github.bancosil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Corrente")
public class Corrente extends Account {

}

