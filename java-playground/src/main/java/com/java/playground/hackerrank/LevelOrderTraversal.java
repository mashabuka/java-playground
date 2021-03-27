package com.java.playground.hackerrank;

import java.util.LinkedList;
import java.util.List;


/**
 * The problem is to print out the node values by level.  That is, all nodes from left to right in the first depth
 * level, then second depth level, and so on.  This can be done a variety of ways including keep a map of level to
 * list of nodes and then print them out.  However, this is effectively the same as a breadth-first search, so it is
 * implemented here as such.
 *
 * <a href="https://www.hackerrank.com/challenges/tree-level-order-traversal/problem">Level order traversal</a>
 * <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first search</a>
 */
public final class LevelOrderTraversal {
  public static void levelOrder(Node root) {
    List<Integer> values = bfs(root);
    StringBuilder sb = new StringBuilder();
    values.forEach(sb::append);
    String result = sb.toString();

    // The hackerrank problem requires you to implement this as a <code>public static</code> method and print out the
    // result.
    System.out.println(result);
  }

  public static List<Integer> bfs(Node root) {
    if (root == null) {
      return List.of();
    }

    // Use a queue to visit frontier nodes before moving to the next depth.
    LinkedList<Node> queue = new LinkedList<>();
    queue.add(root);

    List<Integer> result = new LinkedList<>();
    result.add(root.data);

    while (!queue.isEmpty()) {
      Node u = queue.remove();

      // Enqueue all of the frontier nodes for this node; they will be visited before descending one level lower.
      // Usually breadth-first search will keep track of discovered nodes and skip them if they have already been
      // discovered, but this is a tree and we are guaranteed to not have any cycles.  So we don't need a set to keep
      // track of discovered nodes.
      List<Node> neighbors = getNeighbors(u);
      for (Node v : neighbors) {
        if (v == null) {
          continue;
        }

        // In contrast to depth-first search, we merely enqueue the node and move on to the next without descending
        // down a level recursively to traverse this node.
        queue.add(v);
        result.add(v.data);
      }
    }

    return result;
  }

  private static List<Node> getNeighbors(Node node) {
    List<Node> neighbors = new LinkedList<>();

    if (node.left != null) {
      neighbors.add(node.left);
    }

    if (node.right != null) {
      neighbors.add(node.right);
    }

    return neighbors;
  }

  public static final class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data) {
      this.data = data;
    }
  }
}
