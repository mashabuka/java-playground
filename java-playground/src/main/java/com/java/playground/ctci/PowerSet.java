package com.java.playground.ctci;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Generates all subsets of a set.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Power_set">Power set</a>
 */
public final class PowerSet<T> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PowerSet.class);

  public Set<Set<T>> generate(Set<T> xs) {
    if (xs.isEmpty()) {
      LOGGER.info("The power set of {} is: {}", xs, ImmutableSet.of());
      return ImmutableSet.of(ImmutableSet.of());
    }

    // First find any element x and remove it from the set.
    T x = xs.stream().findFirst().get();
    Set<T> rest = remove(xs, x);

    // Generate all subsets without x, then add e to each of them.
    Set<Set<T>> without = generate(rest);
    Set<Set<T>> with = addElement(without, x);

    // The subsets without x and the subsets with x added will give you the power set.
    Set<Set<T>> result = ImmutableSet.<Set<T>>builder()
        .addAll(without)
        .addAll(with)
        .build();
    LOGGER.info("The power set of {} is: {}", xs, result);
    return result;
  }

  private Set<T> remove(Set<T> set, T e) {
    Set<T> rest = Sets.difference(set, ImmutableSet.of(e));
    return ImmutableSet.copyOf(rest);
  }

  private Set<Set<T>> addElement(Set<Set<T>> without, T x) {
    return without.stream()
        .map(xs -> ImmutableSet.<T>builder()
            .addAll(xs)
            .add(x)
            .build())
        .collect(ImmutableSet.toImmutableSet());
  }
}
