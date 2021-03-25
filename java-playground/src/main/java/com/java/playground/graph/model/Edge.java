package com.java.playground.graph.model;

import java.util.Optional;


/**
 * <p>
 * A simple type for graph theory algorithms.  This code is meant to be used for practice, learning, and
 * interview preparation.  Do not use this as an example for production code.  Consider something like Guava's
 * <code>common.graph</code> package.
 * </p>
 *
 * @see <a href="https://github.com/google/guava/wiki/GraphsExplained">Graphs Explained</a>
 */
public interface Edge<T extends Comparable<T>> {
  String getId();
  Node<T> getSource();
  Node<T> getSink();
  Optional<Double> getWeight();
  boolean isWeighted();
  boolean contains(Node<?> node);
}
