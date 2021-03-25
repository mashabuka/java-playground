package com.java.playground.graph;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.java.playground.graph.model.Graph;
import com.java.playground.graph.model.Line;
import com.java.playground.graph.model.Node;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class BreadthFirstSearchTest {
  @Test
  public void testSearch() {
    Graph<Line<String>, String> g = Graph.<Line<String>, String>builder()
        .addEdge(Line.of("A", "B"))
        .addEdge(Line.of("A", "C"))
        .addEdge(Line.of("A", "E"))
        .addEdge(Line.of("B", "D"))
        .addEdge(Line.of("B", "F"))
        .addEdge(Line.of("C", "G"))
        .build();
    Node<String> root = g.getNodeById("vA");

    List<Node<String>> nodes = BreadthFirstSearch.execute(g, root);

    List<String> result = nodes.stream()
        .map(Node::getValue)
        .collect(ImmutableList.toImmutableList());
    assertThat(result).containsExactly("A", "B", "C", "E", "D", "F", "G");
  }
}
