package com.java.playground.hackerrank;

import java.util.List;

import org.junit.jupiter.api.Test;

import static com.java.playground.hackerrank.LevelOrderTraversal.Node;
import static org.assertj.core.api.Assertions.assertThat;


public final class LevelOrderTraversalTest {
  @Test
  public void testLevelOrder() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);

    List<Integer> result = LevelOrderTraversal.bfs(root);

    assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7);
  }
}
