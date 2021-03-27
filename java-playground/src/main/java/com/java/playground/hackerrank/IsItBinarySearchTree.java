package com.java.playground.hackerrank;

/**
 * <p>
 * Solves the problem of checking if an input is a binary search tree.  A binary search tree's nodes satisfies the
 * property that all values in the left subtree are less than the node's value, and all values in the right subtree are
 * greater than the node's value.  By this definition, no duplicates are allowed.
 * </p>
 *
 * @see <a href="https://www.hackerrank.com/challenges/is-binary-search-tree/problem">Is this a binary search tree?</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_search_tree">Binary search tree</a>
 */
public final class IsItBinarySearchTree {
  public boolean checkBST(Node root) {
    int leftMax = findMax(root.left, root.data);
    int rightMax = findMax(root.right, root.data);

    // Verify the property that the left subtree's max value is less than this node's value, and the right subtree's
    // max value is greater than this node's value.
    return leftMax < root.data && root.data < rightMax;
  }

  private int findMax(Node node, int current) {
    if (node == null) {
      return current;
    }

    int max = Math.max(current, node.data);
    // Recursively find the max value in the left subtree of this node
    max = Math.max(max, findMax(node.left, max));
    // Recursively find the max value in the right subtree of this node
    max = Math.max(max, findMax(node.right, max));
    return max;
  }

  public static final class Node {
    public int data;
    public Node left;
    public Node right;
  }
}
