package com.java.playground.tree.model;

import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;


public final class Node<T extends Comparable<T>> {
  private final T value;
  private final Optional<Node<T>> left;
  private final Optional<Node<T>> right;

  private Node(T value, Optional<Node<T>> left, Optional<Node<T>> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static <T extends Comparable<T>> Node<T> of(T value) {
    return new Node<>(value, Optional.empty(), Optional.empty());
  }

  public static <T extends Comparable<T>> Node<T> of(T value, Node<T> left) {
    return new Node<>(value, Optional.of(left), Optional.empty());
  }

  public static <T extends Comparable<T>> Node<T> of(T value, Node<T> left, Node<T> right) {
    return new Node<>(value, Optional.of(left), Optional.of(right));
  }

  public T getValue() {
    return value;
  }

  public Optional<Node<T>> getLeft() {
    return left;
  }

  public Optional<Node<T>> getRight() {
    return right;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("value", value)
        .add("left", left)
        .add("right", right)
        .toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Node<?>)) {
      return false;
    }

    Node<?> that = (Node<?>) obj;
    return Objects.equals(this.value, that.value)
        && Objects.equals(this.left, that.left)
        && Objects.equals(this.left, that.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, left, right);
  }
}
