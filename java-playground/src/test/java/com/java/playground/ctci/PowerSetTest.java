package com.java.playground.ctci;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class PowerSetTest {
  private final PowerSet<Integer> powerset = new PowerSet<>();

  @Test
  public void testExecute() {
    Set<Integer> xs = ImmutableSet.of(1, 2, 3);

    Set<Set<Integer>> ys = powerset.generate(xs);

    assertThat(ys).containsExactlyInAnyOrder(
        ImmutableSet.of(),
        ImmutableSet.of(1),
        ImmutableSet.of(2),
        ImmutableSet.of(3),
        ImmutableSet.of(1, 2),
        ImmutableSet.of(1, 3),
        ImmutableSet.of(2, 3),
        ImmutableSet.of(1, 2, 3));
  }
}
