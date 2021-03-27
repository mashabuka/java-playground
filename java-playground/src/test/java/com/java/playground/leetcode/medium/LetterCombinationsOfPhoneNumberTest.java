package com.java.playground.leetcode.medium;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class LetterCombinationsOfPhoneNumberTest {
  private final LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();

  @Test
  public void testLetterCombinations() {
    List<String> actual = solution.letterCombinations("2345");

    assertThat(actual).hasSize(81);
  }
}
