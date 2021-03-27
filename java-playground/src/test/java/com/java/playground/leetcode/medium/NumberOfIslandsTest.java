package com.java.playground.leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class NumberOfIslandsTest {
  private final NumberOfIslands solution = new NumberOfIslands();

  @Test
  public void testSolution1() {
    char grid[][] = new char[][] {
        new char[] {'1','1','1','1','0'},
        new char[] {'1','1','0','1','0'},
        new char[] {'1','1','0','0','0'},
        new char[] {'0','0','0','0','0'}};

    int actual = solution.numIslands(grid);

    assertThat(actual).isEqualTo(1);
  }

  @Test
  public void testSolution2() {
    char grid[][] = new char[][] {
        new char[] {'1','1','0','0','0'},
        new char[] {'1','1','0','0','0'},
        new char[] {'0','0','1','0','0'},
        new char[] {'0','0','0','1','1'}};

    int actual = solution.numIslands(grid);

    assertThat(actual).isEqualTo(3);
  }
}
