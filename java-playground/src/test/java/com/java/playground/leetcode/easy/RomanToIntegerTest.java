package com.java.playground.leetcode.easy;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class RomanToIntegerTest {
  private final RomanToInteger solution = new RomanToInteger();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments("III", 3),
        arguments("IV", 4),
        arguments("LVIII", 58),
        arguments("MCMXCIV", 1994),
        arguments("IX", 9));
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testSolution(String input, int expected) {
    int actual = solution.romanToInt(input);

    assertThat(actual).isEqualTo(expected);
  }
}
