package com.java.playground.leetcode;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class BasicCalculator2Test {
  private final BasicCalculator2 solution = new BasicCalculator2();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments("12345", 12345),
        arguments("11*11", 121),
        arguments(" - 1", -1),
        arguments(" 1 + 2 + 3 ", 6),
        arguments(" 0 + 0 + 0 ", 0),
        arguments(" -2 +1", -1),
        arguments(" 1 + 2 - 3 ", 0));
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testCalculate(String input, int expected) {
    int actual = solution.calculate(input);
    assertThat(actual).isEqualTo(expected);
  }
}
