package org.acme.fundamentals.monadicComposition.ex1to5.model;

import lombok.Data;
import java.util.Optional;

@Data
public class Country {

    public Optional<Capital> capital;

    public Country(Optional<Capital> capital){
        this.capital = capital;
    }

}
