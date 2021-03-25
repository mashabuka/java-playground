package com.java.playground.graph.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;


/**
 * <p>
 * A simple type for graph theory algorithms.  This code is meant to be used for practice, learning, and
 * interview preparation.  Do not use this as an example for production code.  Consider something like Guava's
 * <code>common.graph</code> package.
 * </p>
 *
 * @see <a href="https://github.com/google/guava/wiki/GraphsExplained">Graphs Explained</a>
 */
public final class Node<T extends Comparable<T>> {
  private final String id;
  private final T value;

  private Node(String id, T value) {
    this.id = id;
    this.value = value;
  }

  public static <T extends Comparable<T>> Node<T> of(String id, T value) {
    return new Node<T>(id, value);
  }

  public static <T extends Comparable<T>> Node<T> of(T value) {
    return of("v" + value, value);
  }

  public String getId() {
    return id;
  }

  public T getValue() {
    return value;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("value", value)
        .toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Node<?>)) {
      return false;
    }

    Node<?> that = (Node<?>) obj;
    return id.equals(that.id)
        && value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value);
  }
}
