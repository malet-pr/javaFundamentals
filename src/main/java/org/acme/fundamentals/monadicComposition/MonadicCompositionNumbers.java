package org.acme.fundamentals.monadicComposition;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MonadicCompositionNumbers {

    private Optional<Integer> parsePositiveInt(String s){
        try {
            int n = Integer.parseInt(s);
            if(n>0){
                return Optional.of(n);
            }
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private Optional<Double> reciprocal(int value){
        if(value==0){
            return Optional.empty();
        }
        return Optional.of((double) 1/value);
    }

    private Double chain (String s){
        return Optional.of(s).flatMap(this::parsePositiveInt).flatMap(this::reciprocal).orElse(null);
    }

    public void run(String... args) {
        System.out.println(chain("20"));
        System.out.println(chain("-3"));
        System.out.println(chain("hello"));
        System.out.println(chain("0"));
    }


}
