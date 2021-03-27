package com.java.playground.leetcode.hard;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class BasicCalculator1Test {
  private final BasicCalculator1 solution = new BasicCalculator1();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments("12345", 12345),
        arguments(" - 1", -1),
        arguments(" -((1))", -1),
        arguments(" -(3-4)", 1),
        arguments("(1-(2))", -1),
        arguments("1-(-1)", 2),
        arguments("(1+(-1))", 0),
        arguments("(1-(-1))", 2),
        arguments(" 1 + 2 + 3 ", 6),
        arguments(" 0 + 0 + 0 ", 0),
        arguments(" 0 + ((0) + 0) ", 0),
        arguments("(1+(4+5+2)-3)+(6+8)", 23),
        arguments(" -2 +1", -1),
        arguments("(1-(3-4))", 2),
        arguments(" 1 + 2 - 3 ", 0));
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testCalculate(String input, int expected) {
    int actual = solution.calculate(input);
    assertThat(actual).isEqualTo(expected);
  }
}
