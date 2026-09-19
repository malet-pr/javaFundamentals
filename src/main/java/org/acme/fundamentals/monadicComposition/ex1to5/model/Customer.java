package org.acme.fundamentals.monadicComposition.ex1to5.model;


import lombok.Data;

@Data
public class Customer {
    String name;
    Address address;

    public Customer(String name, Address address){
        this.name = name;
        this.address = address;
    }

}



