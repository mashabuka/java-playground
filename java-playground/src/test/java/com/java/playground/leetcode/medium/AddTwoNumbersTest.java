package com.java.playground.leetcode.medium;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.java.playground.leetcode.medium.AddTwoNumbers.ListNode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public final class AddTwoNumbersTest {
  private final AddTwoNumbers problem = new AddTwoNumbers();

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(
        arguments(new int[] {2, 4, 3}, new int[] {5, 6, 4}, "[7, 0, 8]"),
        arguments(new int[] {0}, new int[] {0}, "[0]"),
        arguments(new int[] {9, 9, 9, 9, 9, 9, 9}, new int[] {9, 9, 9, 9}, "[8, 9, 9, 9, 0, 0, 0, 1]"),
        arguments(new int[] {9}, new int[] {1, 9, 9, 9, 9, 9, 9, 9, 9}, "[0, 0, 0, 0, 0, 0, 0, 0, 0, 1]"));
  }

  @ParameterizedTest
  @MethodSource("argumentsProvider")
  public void testExecute(int[] xs, int[] ys, String expected) {
    String actual = run(xs, ys);

    assertThat(actual).isEqualTo(expected);
  }

  private String run(int[] xs, int[] ys) {
    ListNode l1 = toListNode(xs);
    ListNode l2 = toListNode(ys);
    ListNode result = problem.addTwoNumbers(l1, l2);
    return result.toString();
  }

  private ListNode toListNode(int[] xs) {
    ListNode result = null;
    for (int i = xs.length - 1; i >= 0; i--) {
      result = new ListNode(xs[i], result);
    }
    return result;
  }
}
