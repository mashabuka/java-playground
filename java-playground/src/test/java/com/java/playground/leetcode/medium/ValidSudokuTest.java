package com.java.playground.leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class ValidSudokuTest {
  private final ValidSudoku solution = new ValidSudoku();

  @Test
  public void testSolution() {
    char board[][] = new char[][] {
        new char[] {'.','.','.','.','5','.','.','1','.'},
        new char[] {'.','4','.','3','.','.','.','.','.'},
        new char[] {'.','.','.','.','.','3','.','.','1'},
        new char[] {'8','.','.','.','.','.','.','2','.'},
        new char[] {'.','.','2','.','7','.','.','.','.'},
        new char[] {'.','1','5','.','.','.','.','.','.'},
        new char[] {'.','.','.','.','.','2','.','.','.'},
        new char[] {'.','2','.','9','.','.','.','.','.'},
        new char[] {'.','.','4','.','.','.','.','.','.'}};

    boolean actual = solution.isValidSudoku(board);

    assertThat(actual).isFalse();
  }
}
