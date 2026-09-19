package org.acme.fundamentals.monadicComposition.ex1to5.model;

import lombok.Data;

@Data
public class Capital{
    public String name;

    public Capital(String name){
        this.name = name;
    }
}
