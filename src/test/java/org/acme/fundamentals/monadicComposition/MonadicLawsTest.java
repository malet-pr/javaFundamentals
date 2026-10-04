package org.acme.fundamentals.monadicComposition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;


public class MonadicLawsTest {

    static MonadicLawsOptional mlo = new MonadicLawsOptional();

    @ParameterizedTest(name = "{0}")
    @MethodSource("doubleIfPositiveCases")
    @DisplayName("Test the function doubleIfPositive")
    public void doubleIfPositiveTest(String description, Integer number, Optional<Integer> expectedResult) {
        Optional<Integer> result = mlo.doubleIfPositive.apply(number);
        Assertions.assertEquals(expectedResult, result, "Expected " + expectedResult + " but got " + result);
    }

    private static Stream<Arguments> doubleIfPositiveCases() {
        return Stream.of(
                Arguments.of("should return optional with double the positive number", 4, Optional.of(8)),
                Arguments.of("should return empty for negative number", -4, Optional.empty()),
                Arguments.of("Should return empty with value zero", 0, Optional.empty())
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("halfIfEvenCases")
    @DisplayName("Test the function halfIfEven")
    public void halfIfEvenTest(String description, Integer number, Optional<Integer> expectedResult) {
        Optional<Integer> result = mlo.halfIfEven.apply(number);
        Assertions.assertEquals(expectedResult, result, "Expected " + expectedResult + " but got " + result);
    }

    private static Stream<Arguments> halfIfEvenCases() {
        return Stream.of(
                Arguments.of("should return optional with half the even number", 4, Optional.of(2)),
                Arguments.of("should return empty for odd number", 7, Optional.empty()),
                Arguments.of("Should return optional 0 with value 0", 0, Optional.of(0))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("stringifyIfSmallCases")
    @DisplayName("Test the function stringifyIfSmall")
    public void stringifyIfSmallTest(String description, Integer number, Optional<String> expectedResult) {
        Optional<String> result = mlo.stringifyIfSmall.apply(number);
        Assertions.assertEquals(expectedResult, result, "Expected " + expectedResult + " but got " + result);
    }

    private static Stream<Arguments> stringifyIfSmallCases() {
        return Stream.of(
                Arguments.of("should return a String with number less than 25", 4, Optional.of("4")),
                Arguments.of("should return empty for large number", 100, Optional.empty()),
                Arguments.of("Should return empty with value zero", 0, Optional.empty())
        );
    }


    @ParameterizedTest(name = "{0}")
    @MethodSource("firstMonadicLawCases")
    @DisplayName("Test the first monadic law")
    public <T> void firstMonadicLawTest(String description, Integer number, Function<Integer, Optional<T>> function) {
        Optional<T> r1 = function.apply(number);
        Optional<T> r2 = Optional.of(number).flatMap(function);
        Assertions.assertEquals(r1, r2);
    }

    private static Stream<Arguments> firstMonadicLawCases() {
        return Stream.of(
            Arguments.of("should return the same optional number", 4, mlo.doubleIfPositive),
            Arguments.of("should return empty both sides", -4, mlo.doubleIfPositive),
            Arguments.of("Should return empty with value zero", 0, mlo.doubleIfPositive),
            Arguments.of("should return the same optional number", 4, mlo.halfIfEven),
            Arguments.of("should return empty both sides", 7, mlo.halfIfEven),
            Arguments.of("Should return the same Optional[0] with value zero", 0, mlo.halfIfEven),
            Arguments.of("should return the same optional String", 4, mlo.stringifyIfSmall),
            Arguments.of("should return empty both sides", 100, mlo.stringifyIfSmall),
            Arguments.of("Should return empty with value zero", 0, mlo.stringifyIfSmall)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("secondMonadicLawCases")
    @DisplayName("Test the second monadic law")
    public <T> void secondMonadicLawTest(String description, Integer number, Function<Integer, Optional<T>> function) {
        Optional<T> m = function.apply(number);
        Optional<T> left = m.flatMap(Optional::of) ;
        Assertions.assertEquals(m, left);
    }

    private static Stream<Arguments> secondMonadicLawCases() {
        return Stream.of(
                Arguments.of("should return the same positive number", 4, mlo.doubleIfPositive),
                Arguments.of("should return the same negative number", -4, mlo.doubleIfPositive),
                Arguments.of("Should return the same zero", 0, mlo.doubleIfPositive),
                Arguments.of("should return the same positive number", 4, mlo.halfIfEven),
                Arguments.of("should return the same negative number", 7, mlo.halfIfEven),
                Arguments.of("Should return the same zero", 0, mlo.halfIfEven),
                Arguments.of("should return the same small number", 4, mlo.stringifyIfSmall),
                Arguments.of("should return the same large number", 100, mlo.stringifyIfSmall),
                Arguments.of("Should return the same zero", 0, mlo.stringifyIfSmall)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("thirdMonadicLawCases")
    @DisplayName("Test the third monadic law")
    public <T,V,S> void thirdMonadicLawTest(String description, Optional<T> m,
                                            Function<T,Optional<V>> function2, Function<V,Optional<S>> function3) {
        Optional<S> r1 = m.flatMap(function2).flatMap(function3);
        Optional<S> r2 = m.flatMap(x -> function2.apply(x).flatMap(function3));
        Assertions.assertEquals(r1, r2);
    }

    private static Stream<Arguments> thirdMonadicLawCases() {
        return Stream.of(
                Arguments.of("should return the same string", Optional.of(8),mlo.halfIfEven,mlo.stringifyIfSmall),
                Arguments.of("should return optional empty after first function", Optional.of(15),mlo.halfIfEven,mlo.stringifyIfSmall),
                Arguments.of("should return optional empty after second function", Optional.of(60),mlo.halfIfEven,mlo.stringifyIfSmall),
                Arguments.of("Should return propagate optional empty", Optional.empty(),mlo.halfIfEven,mlo.stringifyIfSmall)
        );
    }

}

