package org.acme.fundamentals.monadicComposition;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.function.Function;

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

    private String deriveMap (Optional<Integer> m){
        Function<Integer, String> f = x -> "value=" + x;
        Optional<String> r1 = m.map(f);
        Optional<String> r2 = m.flatMap(x -> Optional.of(f.apply(x)));
        return ("r1 = " + r1.orElse(null) + ", r2 = " + r2.orElse(null));
    }

    public void run(String... args) {
        System.out.println(chain("20"));
        System.out.println(chain("-3"));
        System.out.println(chain("hello"));
        System.out.println(chain("0"));
        System.out.println(deriveMap(Optional.of(20)));
        System.out.println(deriveMap(Optional.empty()));
    }


}
