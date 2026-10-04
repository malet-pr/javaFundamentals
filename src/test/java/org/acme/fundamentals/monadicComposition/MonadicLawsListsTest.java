package org.acme.fundamentals.monadicComposition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class MonadicLawsListsTest {

    static MonadicLawsLists mll = new MonadicLawsLists();
    static final String testStr = "This is a test";

    @ParameterizedTest(name = "{0}")
    @MethodSource("spellString")
    @DisplayName("Test the function spellString")
    public <T,S> void spellStringTest(String description, T str, List<S> expectedResult, Function<T, List<S>> function) {
        List<S> result = function.apply(str);
        Assertions.assertEquals(expectedResult, result, "Expected " + expectedResult + " but got " + result);
    }

    private static Stream<Arguments> spellString() {
        return Stream.of(
                Arguments.of("Should spell string", testStr,
                        List.of('T','h','i','s',' ','i','s',' ','a',' ','t','e','s','t'), mll.spellString),
                Arguments.of("Should return list of spaces for list with blanks", "   ",
                        List.of(' ',' ',' '), mll.spellString),
                Arguments.of("should return empty list for empty string", "", List.of(), mll.spellString),
                Arguments.of("Should list characters without repetition", testStr,
                        List.of('T','h','i','s',' ','a','t','e'), mll.uniqueChars),
                Arguments.of("Should return list of one space for list with blanks", "   ",
                        List.of(' '), mll.uniqueChars),
                Arguments.of("should return empty list for empty string", "", List.of(), mll.uniqueChars),
                Arguments.of("Should list int representation of the characters", testStr,
                        List.of(84, 104, 105, 115, 32, 105, 115, 32, 97, 32, 116, 101, 115, 116), mll.toNumbers),
                Arguments.of("Should return list of 32s for list with blanks", "   ",
                        List.of(32, 32, 32), mll.toNumbers),
                Arguments.of("should return empty list for empty string", "", List.of(), mll.toNumbers)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("firstMonadicLawCases")
    @DisplayName("Test the first monadic law")
    public <T,S> void firstMonadicLawTest(String description, T str, Function<T, List<S>> function) {
        List<S> r1 = function.apply(str);
        List<S> r2 = Stream.of(str).flatMap(s -> function.apply(s).stream()).toList();
        Assertions.assertEquals(r1, r2);
    }

    private static Stream<Arguments> firstMonadicLawCases() {
        return Stream.of(
                Arguments.of("should return the same list of characters", testStr, mll.spellString),
                Arguments.of("should return the same number of spaces", "   ", mll.spellString),
                Arguments.of("Should return the same empty list", "", mll.spellString),
                Arguments.of("should return the same list of characters without repetition", testStr, mll.uniqueChars),
                Arguments.of("should return the same list of one space", "   ", mll.uniqueChars),
                Arguments.of("Should return the same empty list", "", mll.uniqueChars),
                Arguments.of("should return the same list of numbers", testStr, mll.toNumbers),
                Arguments.of("should return the same list of 32s", "   ", mll.toNumbers),
                Arguments.of("Should return the same empty list", "", mll.toNumbers)
        );
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("secondMonadicLawCases")
    @DisplayName("Test the second monadic law")
    public <T,S> void secondMonadicLawTest(String description, T str, Function<T, List<S>> function) {
        List<S> m = function.apply(str);
        List<S> left = m.stream().flatMap(Stream::of).toList() ;
        Assertions.assertEquals(m, left);
    }

    private static Stream<Arguments> secondMonadicLawCases() {
        return Stream.of(
                Arguments.of("should return the same list of characters", testStr, mll.spellString),
                Arguments.of("should return the same number of spaces", "   ", mll.spellString),
                Arguments.of("Should return the same empty list", "", mll.spellString),
                Arguments.of("should return the same list of characters without repetition", testStr, mll.uniqueChars),
                Arguments.of("should return the same list of one space", "   ", mll.uniqueChars),
                Arguments.of("Should return the same empty list", "", mll.uniqueChars),
                Arguments.of("should return the same list of numbers", testStr, mll.toNumbers),
                Arguments.of("should return the same list of 32s", "   ", mll.toNumbers),
                Arguments.of("Should return the same empty list", "", mll.toNumbers)
        );
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("thirdMonadicLawCases")
    @DisplayName("Test the third monadic law")
    public <T,V,S> void thirdMonadicLawTest(String description, T str,
                                    Function<T,List<V>> function1,
                                    Function<V, List<S>> function2) {
        List<S> r1 = Stream.of(str)
                .flatMap(s -> function1.apply(s).stream())
                .flatMap(t -> function2.apply(t).stream())
                .toList() ;
        List<S> r2 = Stream.of(str)
                .flatMap(x -> function1.apply(x).stream().flatMap(t -> function2.apply(t).stream()))
                .toList();
        Assertions.assertEquals(r1, r2);
    }

    private static Stream<Arguments> thirdMonadicLawCases() {
        return Stream.of(
                Arguments.of("should return the same string", testStr,mll.uniqueChars,mll.charToNumber),
                Arguments.of("should return optional empty after first function", "   ",mll.uniqueChars,mll.charToNumber),
                Arguments.of("should return optional empty after second function", "",mll.uniqueChars,mll.charToNumber)
        );
    }



}
