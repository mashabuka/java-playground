package com.java.playground.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.java.playground.graph.model.Edge;
import com.java.playground.graph.model.Graph;
import com.java.playground.graph.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * A simple type for graph theory algorithms.  This code is meant to be used for practice, learning, and
 * interview preparation.  Do not use this as an example for production code.  Consider something like Guava's
 * <code>common.graph</code> package.
 * </p>
 *
 * <p>
 * This algorithm does not detect cycles; you can use Kahn's algorithm for that.
 * </p>
 *
 * @see <a href="https://github.com/google/guava/wiki/GraphsExplained">Graphs Explained</a>
 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first Search</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting#Algorithms">Kahn's algorithm</a>
 */
public final class DepthFirstSearch<E extends Edge<T>, T extends Comparable<T>> {
  private static final Logger LOGGER = LoggerFactory.getLogger(DepthFirstSearch.class);
  private final Graph<E, T> graph;
  private final Set<Node<T>> discovered;

  public DepthFirstSearch(Graph<E, T> graph) {
    this.graph = graph;
    this.discovered = new HashSet<>();
  }

  public static <E extends Edge<T>, T extends Comparable<T>> void execute(
      Graph<E, T> graph,
      Node<T> root,
      Consumer<Node<T>> preOrderVisitFunction,
      Consumer<Node<T>> postOrderVisitFunction) {

    DepthFirstSearch<E, T> dfs = new DepthFirstSearch<>(graph);
    dfs.execute(root, preOrderVisitFunction, postOrderVisitFunction);
  }

  public static <E extends Edge<T>, T extends Comparable<T>> void execute(
      Graph<E, T> graph,
      Node<T> root,
      Consumer<Node<T>> visitFunction) {
    Consumer<Node<T>> emptyFunction = node -> { };
    // Visiting the nodes in discovered order would be the same as a pre-order visit only.
    execute(graph, root, visitFunction, emptyFunction);
  }

  public static <E extends Edge<T>, T extends Comparable<T>> List<Node<T>> execute(
      Graph<E, T> graph,
      Node<T> root) {
    List<Node<T>> nodes = new LinkedList<>();
    execute(graph, root, nodes::add);
    return ImmutableList.copyOf(nodes);
  }

  private void execute(
      Node<T> u,
      Consumer<Node<T>> preOrderVisitFunction,
      Consumer<Node<T>> postOrderVisitFunction) {
    discovered.add(u);

    // A "preordering" is a list of nodes in the order they were discovered in.  This is the same as a "lexicographical"
    // ordering, which is a generalization of alphabetical ordering applied to generic elements.  Reversing this
    // ordering would give you a "reverse preordering" (which is not the same as a "postordering").
    LOGGER.info("Pre-order visited: {}", u);
    preOrderVisitFunction.accept(u);

    List<Node<T>> neighbors = getNeighbors(u);
    LOGGER.debug("Visiting neighbors of {} that have not been discovered: {}", u, neighbors);
    for (Node<T> v : neighbors) {
      if (discovered.contains(v)) {
        continue;
      }

      // Rather than add this neighbor to a queue as in breadth-first search, descend into this level and start a
      // depth-first search from here.
      execute(v, preOrderVisitFunction, postOrderVisitFunction);
    }

    // A "postordering" is a list of nodes in the order that they were visited in.  That is, after all of its neighbors
    // have been discovered and visited.  Reversing this ordering would give you a "reverse postordering", or a
    // topological sort.
    LOGGER.info("Post-order visited: {}", u);
    postOrderVisitFunction.accept(u);
  }

  private List<Node<T>> getNeighbors(Node<T> u) {
    // The order that the outgoing edges come back is not guaranteed; we sort on node values to get a consistent visit
    // order to verify in our tests.
    List<E> edges = graph.getOutgoingEdges(u);
    return edges.stream()
        .map(Edge::getSink)
        .sorted(Comparator.comparing(Node::getValue))
        .collect(ImmutableList.toImmutableList());
  }
}
