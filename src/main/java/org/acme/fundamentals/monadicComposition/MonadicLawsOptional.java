package org.acme.fundamentals.monadicComposition;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MonadicLawsOptional {

    Function<Integer, Optional<Integer>> doubleIfPositive = i -> {
        if (i > 0) return Optional.of(2 * i);
        return Optional.empty();
    };

    Function<Integer, Optional<Integer>> halfIfEven = i -> {
        if (i % 2 == 0) return Optional.of(i / 2);
        return Optional.empty();
    };

    Function<Integer, Optional<String>> stringifyIfSmall = i -> {
        if (i > 0 && i < 25) return Optional.of(i.toString());
        return Optional.empty();
    };

    public void run(String... args) {
        System.out.println("First Monadic Law:");
        Optional<Integer> r1 = doubleIfPositive.apply(4);
        Optional<Integer> r2 = Optional.of(4).flatMap(doubleIfPositive);
        System.out.println("r1: " + r1 + " - r2: " + r2);
        Optional<Integer> r3 = doubleIfPositive.apply(-4);
        Optional<Integer> r4 = Optional.of(-4).flatMap(doubleIfPositive);
        System.out.println("r3: " + r3 + " - r4: " + r4);
        System.out.println("Second Monadic Law:");
        Optional<Integer> m1 = doubleIfPositive.apply(4);
        Optional<Integer> left1 = m1.flatMap(Optional::of);
        System.out.println(m1.get().equals(left1.get()) ? "holds for positive" : "doesn't hold for positive");
        Optional<Integer> m2 = doubleIfPositive.apply(4);
        Optional<Integer> left2 = m2.flatMap(Optional::of);
        System.out.println(m2.get().equals(left2.get()) ? "holds for negative" : "doesn't hold for negative");
        System.out.println("Third Monadic Law:");
        Optional<Integer> m3 = doubleIfPositive.apply(4);
        Optional<String> r5 = m3.flatMap(halfIfEven).flatMap(stringifyIfSmall);
        Optional<String> r6 = m3.flatMap(x -> halfIfEven.apply(x).flatMap(stringifyIfSmall));
        System.out.println("r5: " + r5 + " - r6: " + r6);
        System.out.println("same type?: " + (r5 instanceof Optional<String> == r5 instanceof Optional<String>));
        Optional<Integer> m4 = doubleIfPositive.apply(-4);
        Optional<String> r7 = m4.flatMap(halfIfEven).flatMap(stringifyIfSmall);
        Optional<String> r8 = m4.flatMap(x -> halfIfEven.apply(x).flatMap(stringifyIfSmall));
        System.out.println("r7: " + r7 + " - r8: " + r8);
        System.out.println("same type?: " + (r7 instanceof Optional<String> == r8 instanceof Optional<String>));
    }

}