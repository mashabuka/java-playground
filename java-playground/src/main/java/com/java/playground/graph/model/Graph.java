package com.java.playground.graph.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * A simple type for graph theory algorithms.  This code is meant to be used for practice, learning, and
 * interview preparation.  Do not use this as an example for production code.  Consider something like Guava's
 * <code>common.graph</code> package.
 * </p>
 *
 * @see <a href="https://github.com/google/guava/wiki/GraphsExplained">Graphs Explained</a>
 */
public final class Graph<E extends Edge<T>, T extends Comparable<T>> {
  private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);
  private final Set<Node<T>> nodes;
  private final Set<E> edges;
  private final Map<String, Node<T>> nodeMap;
  private final Map<String, E> edgeMap;
  private final Map<Node<T>, List<E>> outgoingEdgeMap;

  private Graph(Set<Node<T>> nodes, Set<E> edges) {
    this.nodes = ImmutableSet.copyOf(nodes);
    this.edges = ImmutableSet.copyOf(edges);
    this.nodeMap = nodes.stream().collect(ImmutableMap.toImmutableMap(Node::getId, Function.identity()));
    this.edgeMap = edges.stream().collect(ImmutableMap.toImmutableMap(Edge::getId, Function.identity()));
    this.outgoingEdgeMap = initialize();

    LOGGER.info("Created graph: {}", this);
  }

  public static <E extends Edge<T>, T extends Comparable<T>> Builder<E, T> builder() {
    return new Builder<>();
  }

  public Set<Node<T>> getNodes() {
    return nodes;
  }

  public Set<E> getEdges() {
    return edges;
  }

  public List<E> getOutgoingEdges(Node<T> source) {
    return outgoingEdgeMap.get(source);
  }

  public Node<T> getNodeById(String id) {
    return nodeMap.get(id);
  }

  public E getEdgeById(String id) {
    return edgeMap.get(id);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("nodes", nodes)
        .add("edges", edges)
        .toString();
  }

  private Map<Node<T>, List<E>> initialize() {
    Map<Node<T>, List<E>> map = new HashMap<>();
    edges.forEach(edge -> {
      addOutgoingEdge(edge.getSource(), edge, map);
      if (edge instanceof Line) {
        addOutgoingEdge(edge.getSink(), edge, map);
      }
    });

    return map.entrySet()
        .stream()
        .collect(ImmutableMap.toImmutableMap(
            entry -> entry.getKey(),
            entry -> entry.getValue().stream().collect(ImmutableList.toImmutableList())));
  }

  private void addOutgoingEdge(Node<T> source, E outgoing, Map<Node<T>, List<E>> map) {
    List<E> outgoings = map.get(source);
    if (outgoings == null) {
      outgoings = new LinkedList<>();
    }

    outgoings.add(outgoing);
    map.put(source, outgoings);
  }

  public static final class Builder<E extends Edge<T>, T extends Comparable<T>> {
    private final Set<Node<T>> nodes;
    private final Set<E> edges;
    private Optional<E> first;

    public Builder() {
      nodes = new HashSet<>();
      edges = new HashSet<>();
      first = Optional.empty();
    }

    public Builder<E, T> addNode(Node<T> node) {
      nodes.add(node);
      return this;
    }

    public Builder<E, T> addNode(T value) {
      addNode(Node.of(value));
      return this;
    }

    public Builder<E, T> addEdge(E edge) {
      if (first.isPresent()) {
        checkEdge(first.get(), edge);
      }

      addNode(edge.getSource());
      addNode(edge.getSink());
      edges.add(edge);
      first = Optional.of(edge);
      return this;
    }

    public Graph<E, T> build() {
      return new Graph<>(nodes, edges);
    }

    private void checkEdge(E existing, E edge) {
      if (existing instanceof Arc<?> && edge instanceof Line<?>) {
        throw new IllegalStateException("Cannot add a line to a directed graph.");
      }

      if (existing instanceof Line<?> && edge instanceof Arc<?>) {
        throw new IllegalStateException("Cannot add an arc to an undirected graph.");
      }
    }
  }
}
