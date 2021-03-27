package com.java.playground.leetcode.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * Finding number of islands in a grid is same as finding number of connected components in a graph.  Can be modeled
 * as looking for connected components in a graph, solved using depth-first search on each node.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/number-of-islands/">Number of islands</a>
 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
 * @see <a href="https://en.wikipedia.org/wiki/Component_(graph_theory)">Component (graph theory)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Connectivity_(graph_theory)">Connectivity (graph theory)</a>
 */
public final class NumberOfIslands {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    boolean discovered[][] = new boolean[grid.length][grid[0].length];
    int islands = 0;

    // i == grid.length
    // j == grid[i].length
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        // Ignore 0; it's not a vertex
        if (grid[i][j] == '0') {
          continue;
        }

        // Ignore any vertex already discovered by depth-first search.
        if (discovered[i][j]) {
          continue;
        }

        // For each vertex, perform depth-first search.  When depth-first search completes, increment number of islands
        // by 1.
        dfs(discovered, grid, i, j);
        islands++;
      }
    }

    return islands;
  }

  private void dfs(boolean[][] discovered, char[][] grid, int vi, int vj) {
    discovered[vi][vj] = true;
    List<Vertex> neighbors = getNeighbors(grid, vi, vj);
    for (Vertex neighbor : neighbors) {
      // Ignore any vertex already discovered by dfs
      if (discovered[neighbor.i][neighbor.j] == true) {
        continue;
      }

      dfs(discovered, grid, neighbor.i, neighbor.j);
    }
  }

  private List<Vertex> getNeighbors(char[][] grid, int vi, int vj) {
    List<Vertex> neighbors = new LinkedList<>();
    neighbors.add(new Vertex(vi, vj + 1));
    neighbors.add(new Vertex(vi, vj - 1));
    neighbors.add(new Vertex(vi + 1, vj));
    neighbors.add(new Vertex(vi - 1, vj));

    return neighbors.stream()
        // Check if within boundaries of grid
        .filter(v -> checkBounds(grid, v.i, v.j))
        // Check if an actual vertex exists here as a neighbor
        .filter(v -> grid[v.i][v.j] == '1')
        .collect(Collectors.toList());
  }

  private boolean checkBounds(char[][] grid, int i, int j) {
    if (i < 0 || i > grid.length - 1) {
      return false;
    }

    if (j < 0 || j > grid[0].length - 1) {
      return false;
    }

    return true;
  }

  private static final class Vertex {
    public int i;
    public int j;

    public Vertex(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
}
