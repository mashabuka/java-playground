package com.java.playground.hackerrank;

/**
 * <p>
 * Computes the height of a binary tree.  Note that the tree may be unbalanced.
 * </p>
 *
 * <p>
 * Only functions and classes available in the JDK are permitted to solve these problems.
 * </p>
 *
 * @see <a href="https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem">Tree height of a binary tree</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_tree">Binary tree</a>
 */
public final class TreeHeight {
  public static void height(Node root) {
    // The hackerrank problem requires you to implement this as a <code>public static</code> method and print out the
    // result.  We can calculate the height by descending down the left/right subtrees from the root and calculating
    // the max depth we reach.
    System.out.println(getDepth(root));
  }

  public static int getDepth(Node root) {
    return getDepth(root, 0 /* currentDepth */, 0 /* maxDepth */);
  }

  private static int getDepth(Node node, int currentDepth, int maxDepth) {
    // If the parent node was a leaf node, then the depth is the max depth we have seen so far.
    if (node == null) {
      return maxDepth;
    }

    // Compute the max depth for the left subtree by descending into the left node while updating the max depth seen
    // so far.
    int leftMax = getDepth(
        node.left,
        currentDepth + 1,
        Math.max(currentDepth, maxDepth));

    // Compute the max depth for the right subtree by descending into right right node while updating the max depth
    // seen so far.
    int rightMax = getDepth(
        node.right,
        currentDepth + 1,
        Math.max(currentDepth, maxDepth));

    // The max depth for a non-null node is the max of the depth for the left/right subtrees.
    return Math.max(leftMax, rightMax);
  }

  // Note that for this problem, a node's value is immaterial.
  public static final class Node {
    public Node left;
    public Node right;
  }
}
