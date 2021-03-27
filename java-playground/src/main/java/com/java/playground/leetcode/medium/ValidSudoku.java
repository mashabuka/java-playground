package com.java.playground.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * <p>
 * Checks if board has committed sudoku.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/valid-sudoku/">Valid sudoku</a>
 * @see <a href="https://en.wikipedia.org/wiki/Sudoku">Sudoku</a>
 */
public final class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    Map<Integer, Set<Character>> columns = new HashMap<>();
    Map<Integer, Set<Character>> subboards = new HashMap<>();

    // 2D arrays in Java will look like this:
    // new int[][] cs = new int[][] {
    //    new int[] {0, 1, 2, 3} // row 1
    //    new int[] {4, 5, 6, 7} // row 2
    // };
    // First index i will give you the ith row.
    // Second index j will give you the jth column.
    for (int i = 0; i < board.length; i++) {
      Set<Character> row = new HashSet<>();
      for (int j = 0; j < board[i].length; j++) {
        char c = board[i][j];

        if (c == '.') {
          continue;
        }

        Set<Character> column = columns.get(j);
        if (column == null) {
          column = new HashSet<>();
          columns.put(j, column);
        }

        int si = getSubboard(i, j);
        Set<Character> subboard = subboards.get(si);
        if (subboard == null) {
          subboard = new HashSet<>();
          subboards.put(si, subboard);
        }

        if (row.contains(c) || column.contains(c) || subboard.contains(c)) {
          return false;
        }

        row.add(c);
        column.add(c);
        subboard.add(c);
      }
    }

    return true;
  }

  private int getSubboard(int i, int j) {
    // 0,1,2
    // 3,4,5
    // 6,7,8
    int row = i / 3;
    int column = j / 3;
    return Objects.hash(row, column);
  }
}
