package com.java.playground.leetcode.medium;


/**
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">Lowest common ancestor of a binary tree</a>
 */
public final class LowestCommonAncestorOfBinaryTree {
  private TreeNode ancestor;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    findNodesUnderSubtree(root, p, q);
    return ancestor;
  }

  private boolean findNodesUnderSubtree(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) {
      return false;
    }

    // Found at current node?
    boolean current = (node == p || node == q);
    // Found at left subtree?
    boolean left = findNodesUnderSubtree(node.left, p, q);
    // Found at right subtree?
    boolean right = findNodesUnderSubtree(node.right, p, q);

    // If we found in both left and right subtrees, this is the node
    if (left && right) {
      ancestor = node;
    }

    // If we found in left subtree, but also the current node matches, this current node is *also* the ancestor
    if (current && left) {
      ancestor = node;
    }

    // If we found in right subtree, but also the current node matches, this current node is *also* the ancestor
    if (current && right) {
      ancestor = node;
    }

    // If we found anywhere in the subtrees (or here), return true
    return current || left || right;
  }

  private static final class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
      this.val = val;
    }
  }
}
