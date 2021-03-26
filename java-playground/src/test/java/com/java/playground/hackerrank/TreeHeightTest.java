package com.java.playground.hackerrank;

import org.junit.jupiter.api.Test;

import static com.java.playground.hackerrank.TreeHeight.Node;
import static org.assertj.core.api.Assertions.assertThat;


public final class TreeHeightTest {
  @Test
  public void testTreeHeight() {
    Node root = new Node();
    root.left = new Node();
    root.right = new Node();
    root.left.left = new Node();
    root.left.right = new Node();
    root.right.left = new Node();
    root.right.right = new Node();
    root.right.right.right = new Node();
    root.right.right.right.right = new Node();

    int actual = TreeHeight.getDepth(root);

    assertThat(actual).isEqualTo(4);
  }
}
