package com.java.playground.leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * <p>
 * Merges k sorted lists.  Same as the merge part of merge sort.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/merge-k-sorted-lists/">Merge k sorted lists</a>
 * @see <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge sort</a>
 */
public final class MergeSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    // If all the heads are in a priority queue, you can constantly replace a node with the next node in that same list,
    // and you don't need to keep track of the indices.
    PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparing(node -> node.val));
    for (int i = 0; i < lists.length; i++) {
      if (lists[i] == null) {
        continue;
      }

      q.add(lists[i]);
    }

    // Now just merge the nodes, keeping a reference to the head, and appending to the last.
    ListNode head = null;
    ListNode last = null;
    while (!q.isEmpty()) {
      ListNode smallest = q.remove();
      if (smallest.next != null) {
        q.add(smallest.next);
      }

      // Save the head so we can return it later
      if (head == null) {
        last = smallest;
        head = smallest;
        head.next = null;
        continue;
      }

      // Set the next to the newly popped node
      last.next = smallest;

      // Advance the pointer of last, and set the end of the list
      if (last != null) {
        last = last.next;
        last.next = null;
      }
    }

    return head;
  }

  public static final class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
