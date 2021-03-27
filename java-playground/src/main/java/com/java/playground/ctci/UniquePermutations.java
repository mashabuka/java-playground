package com.java.playground.ctci;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Generates unique permutations of a list of elements.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Permutation">Permutation</a>
 */
public final class UniquePermutations<T> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UniquePermutations.class);

  public Set<List<T>> generate(List<T> xs) {
    if (xs.isEmpty()) {
      LOGGER.info("The permutations of {} are: {}", xs, ImmutableSet.of());
      return ImmutableSet.of();
    }

    if (xs.size() == 1) {
      LOGGER.info("The permutations of {} are: {}", xs, xs);
      return ImmutableSet.of(xs);
    }

    Set<List<T>> result = new HashSet<>();
    // For each of the elements, remove that element, then generate all permutations without that element.  Next insert
    // the element into the beginning of the list.
    for (int i = 0; i < xs.size(); i++) {
      // First, remove the ith element.
      T x = xs.get(i);
      List<T> without = ImmutableList.<T>builder()
          .addAll(xs.subList(0, i))
          .addAll(xs.subList(i + 1, xs.size()))
          .build();

      // After removing the ith element, generate all permutations of the list without the ith element.  Afterwards,
      // add the ith element to the beginning of the list.
      Set<List<T>> permutations = generate(without)
          .stream()
          .map(ps -> prepend(ps, x))
          .collect(ImmutableSet.toImmutableSet());
      result.addAll(permutations);
    }

    LOGGER.info("The permutations of {} are: {}", xs, result);
    return ImmutableSet.copyOf(result);
  }

  private List<T> prepend(List<T> xs, T x) {
    return ImmutableList.<T>builder()
        .add(x)
        .addAll(xs)
        .build();
  }
}
