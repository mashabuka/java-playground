package com.java.playground.ctci;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class UniquePermutationsTest {
  private final UniquePermutations permutations = new UniquePermutations();

  @Test
  public void testPermutations() {
    Set<List<Character>> xs = permutations.generate(ImmutableList.of('a', 'b', 'c'));

    assertThat(xs).containsExactlyInAnyOrder(
        ImmutableList.of('a', 'b', 'c'),
        ImmutableList.of('a', 'c', 'b'),
        ImmutableList.of('b', 'a', 'c'),
        ImmutableList.of('b', 'c', 'a'),
        ImmutableList.of('c', 'b', 'a'),
        ImmutableList.of('c', 'a', 'b'));
  }
}
