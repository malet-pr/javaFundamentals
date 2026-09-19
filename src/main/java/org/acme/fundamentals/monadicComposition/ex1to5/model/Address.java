package org.acme.fundamentals.monadicComposition.ex1to5.model;

import lombok.Data;

@Data
public class Address{
    public String city;
    public Country country;

    public Address(String city, Country country){
        this.city = city;
        this.country = country;
    }
}