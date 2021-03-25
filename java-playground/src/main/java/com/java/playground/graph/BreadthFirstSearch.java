package com.java.playground.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
 * Unlike depth-first search, no recursion is required here.  It explores neighbor nodes at the current depth before
 * moving on to the next depth level.
 * </p>
 *
 * @see <a href="https://github.com/google/guava/wiki/GraphsExplained">Graphs Explained</a>
 * @see <a href="https://en.wikipedia.org/wiki/Breadth-first_search">Breadth-first Search</a>
 */
public final class BreadthFirstSearch<E extends Edge<T>, T extends Comparable<T>> {
  private static final Logger LOGGER = LoggerFactory.getLogger(BreadthFirstSearch.class);
  private final Graph<E, T> graph;
  private final Set<Node<T>> discovered;

  private BreadthFirstSearch(Graph<E, T> graph) {
    this.graph = graph;
    this.discovered = new HashSet<>();
  }

  public static <E extends Edge<T>, T extends Comparable<T>> void execute(
      Graph<E, T> graph,
      Node<T> root,
      Consumer<Node<T>> visitFunction) {
    BreadthFirstSearch<E, T> bfs = new BreadthFirstSearch<>(graph);
    bfs.execute(root, visitFunction);
  }

  public static <E extends Edge<T>, T extends Comparable<T>> List<Node<T>> execute(
      Graph<E, T> graph,
      Node<T> root) {
    List<Node<T>> ordered = new LinkedList<>();
    Consumer<Node<T>> visitFunction = ordered::add;

    BreadthFirstSearch<E, T> bfs = new BreadthFirstSearch<>(graph);
    bfs.execute(root, visitFunction);

    return ImmutableList.copyOf(ordered);
  }

  private void execute(Node<T> root, Consumer<Node<T>> visitFunction) {
    // Use a queue to visit frontier nodes before moving to the next depth.
    Queue<Node<T>> queue = new LinkedList<>();
    queue.add(root);

    LOGGER.info("Visiting the root node: {}", root);
    visitFunction.accept(root);
    discovered.add(root);

    while (!queue.isEmpty()) {
      Node<T> u = queue.remove();

      // Enqueue all of the frontier nodes for this node; they will be visited before descending one level lower.
      for (Node<T> v : getNeighbors(u)) {
        if (discovered.contains(v)) {
          continue;
        }

        LOGGER.info("Visiting node: {}", v);
        visitFunction.accept(v);
        discovered.add(v);
        queue.add(v);
      }
    }
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
