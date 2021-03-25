package com.java.playground.graph.model;

import java.util.Objects;
import java.util.Optional;

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
public final class Arc<T extends Comparable<T>> implements Edge<T> {
  private final String id;
  private final Node<T> source;
  private final Node<T> sink;
  private final Optional<Double> weight;

  private Arc(Node<T> source, Node<T> sink, Optional<Double> weight) {
    this.id = "(" + source.getId() + "," + sink.getId() + ")";
    this.source = source;
    this.sink = sink;
    this.weight = weight;
  }

  public static <T extends Comparable<T>> Arc<T> of(Node<T> source, Node<T> sink, double weight) {
    return new Arc<>(source, sink, Optional.of(weight));
  }

  public static <T extends Comparable<T>> Arc<T> of(T source, T sink, double weight) {
    return of(Node.of(source), Node.of(sink), weight);
  }

  public static <T extends Comparable<T>> Arc<T> of(Node<T> source, Node<T> sink) {
    return new Arc<>(source, sink, Optional.empty());
  }

  public static <T extends Comparable<T>> Arc<T> of(T source, T sink) {
    return of(Node.of(source), Node.of(sink));
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Node<T> getSource() {
    return source;
  }

  @Override
  public Node<T> getSink() {
    return sink;
  }

  @Override
  public Optional<Double> getWeight() {
    return weight;
  }

  @Override
  public boolean isWeighted() {
    return weight.isPresent();
  }

  @Override
  public boolean contains(Node<?> node) {
    return node.equals(source) || node.equals(sink);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("source", source)
        .add("sink", sink)
        .add("weight", weight)
        .toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Arc<?>)) {
      return false;
    }

    Arc<?> that = (Arc<?>) obj;
    return Objects.equals(id, that.id)
        && Objects.equals(source, that.source)
        && Objects.equals(sink, that.sink)
        && Objects.equals(weight, that.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, source, sink, weight);
  }
}
