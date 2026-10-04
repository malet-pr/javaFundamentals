package org.acme.fundamentals.monadicComposition;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;

@Service
public class MonadicLawsLists {

    Function<String, List<Character>> spellString = str ->
            str.chars().mapToObj(e->(char)e).toList();

    Function<String, List<Character>> uniqueChars = str ->
            str.chars().mapToObj(e->(char)e).distinct().toList();

    Function<String, List<Integer>> toNumbers = str ->
            str.chars().boxed().toList();

    Function<Character, List<Integer>> charToNumber =
            c -> List.of((int) c);

}
