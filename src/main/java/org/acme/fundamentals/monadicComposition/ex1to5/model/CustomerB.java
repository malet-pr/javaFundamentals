package org.acme.fundamentals.monadicComposition.ex1to5.model;

import lombok.Data;
import java.util.Optional;

@Data
public class CustomerB {
    String name;
    Optional<Address> address;

    public CustomerB(String name, Optional<Address> address){
        this.name = name;
        this.address = address;
    }

}
