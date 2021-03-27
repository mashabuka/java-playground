package com.java.playground.leetcode;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class RegularExpressionMatchingTest {
  private final RegularExpressionMatching solution = new RegularExpressionMatching();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments("abc", "abc", true),
        arguments("abc", "ab", false),
        arguments("a", "abc", false),
        arguments("a", ".", true),
        arguments("a", ".*", true),
        arguments("a", "a*", true),
        arguments("a", "a*a", true),
        arguments("ab", ".*c", false)
    );
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testSolution(String text, String pattern, boolean expected) {
    assertThat(solution.isMatch(text, pattern)).isEqualTo(expected);
  }
}
