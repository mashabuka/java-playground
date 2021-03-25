package com.java.playground.graph;

import java.util.LinkedList;
import java.util.List;

import com.java.playground.graph.model.Graph;
import com.java.playground.graph.model.Line;
import com.java.playground.graph.model.Node;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class DepthFirstSearchTest {
  @Test
  public void testSearch() {
    Graph<Line<String>, String> graph = Graph.<Line<String>, String>builder()
        .addEdge(Line.of("A", "B"))
        .addEdge(Line.of("A", "C"))
        .addEdge(Line.of("A", "E"))
        .addEdge(Line.of("B", "D"))
        .addEdge(Line.of("B", "F"))
        .addEdge(Line.of("C", "G"))
        .build();
    Node<String> root = graph.getNodeById("vA");
    List<String> preOrdered = new LinkedList<>();
    List<String> postOrdered = new LinkedList<>();

    DepthFirstSearch.execute(
        graph,
        root,
        node -> preOrdered.add(node.getValue()),
        node -> postOrdered.add(node.getValue()));

    assertThat(preOrdered).containsExactly("A", "B", "D", "F", "C", "G", "E");
    assertThat(postOrdered).containsExactly("D", "F", "B", "G", "C", "E", "A");
  }
}
