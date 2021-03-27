package com.java.playground.leetcode.medium;

import org.junit.jupiter.api.Test;

import static com.java.playground.leetcode.medium.MergeSortedLists.ListNode;
import static org.assertj.core.api.Assertions.assertThat;


public final class MergeSortedListsTest {
  private final MergeSortedLists solution = new MergeSortedLists();

  @Test
  public void testMerge() {
    ListNode[] lists = new ListNode[3];
    lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
    lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
    lists[2] = new ListNode(2, new ListNode(6));

    ListNode merged = solution.mergeKLists(lists);

    ListNode current = merged;
    int nodes = 0;
    while (current != null) {
      current = current.next;
      nodes++;
    }
    assertThat(nodes).isEqualTo(8);
  }
}
