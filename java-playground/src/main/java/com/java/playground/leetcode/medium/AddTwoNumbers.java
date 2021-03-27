package com.java.playground.leetcode.medium;

import java.util.Stack;


/**
 * @see <a href="https://leetcode.com/problems/add-two-numbers/">Add two numbers</a>
 */
public final class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int x = toInteger(l1);
    int y = toInteger(l2);
    ListNode result = toListNode(x + y);
    return result;
  }

  private int toInteger(ListNode node) {
    ListNode current = node;
    int result = 0;
    int base = 1;
    while (current != null) {
      result += base * current.val;
      base *= 10;
      current = current.next;
    }

    return result;
  }

  private ListNode toListNode(int x) {
    if (x == 0) {
      return new ListNode(0);
    }

    Stack<Integer> s = new Stack<>();
    int current = x;

    while (current > 0) {
      s.push(current % 10);
      current /= 10;
    }

    ListNode result = null;
    while (!s.empty()) {
      result = new ListNode(s.pop(), result);
    }

    return result;
  }

  public static final class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");

      ListNode current = this;
      while (current != null) {
        sb.append(current.val);
        current = current.next;

        if (current != null) {
          sb.append(", ");
        }
      }

      sb.append("]");
      return sb.toString();
    }
  }
}
