package com.java.playground.leetcode.easy;

import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class TwoSumTest {
  private final TwoSum problem = new TwoSum();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments(9, ImmutableList.of(2, 7, 11, 15), ImmutableList.of(0, 1)),
        arguments(6, ImmutableList.of(3, 2, 4), ImmutableList.of(1, 2)),
        arguments(6, ImmutableList.of(3, 3), ImmutableList.of(0, 1)));
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testExecute(int target, List<Integer> xs, List<Integer> expected) {
    int[] result = problem.twoSum(xs.stream().mapToInt(x -> x).toArray(), target);
    List<Integer> actual = Ints.asList(result);

    assertThat(actual).isEqualTo(expected);
  }
}
