package com.java.playground.graph.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class GraphTest {
  @Test
  public void testUndirectedGraph() {
    Graph<Line<Integer>, Integer> g = Graph.<Line<Integer>, Integer>builder()
        .addEdge(Line.of(0, 1, 100D))
        .addEdge(Line.of(1, 2, 100D))
        .addEdge(Line.of(2, 0, 100D))
        .build();

    assertThat(g.getNodes()).hasSize(3);
    assertThat(g.getEdges()).hasSize(3);
    assertThat(g.getOutgoingEdges(Node.of(0))).hasSize(2);
    assertThat(g.getOutgoingEdges(Node.of(1))).hasSize(2);
    assertThat(g.getOutgoingEdges(Node.of(2))).hasSize(2);
    g.getEdges().forEach(line -> assertThat(line.getWeight()).hasValue(100D));
  }

  @Test
  public void testDirectedGraph() {
    Graph<Arc<Integer>, Integer> g = Graph.<Arc<Integer>, Integer>builder()
        .addEdge(Arc.of(0, 1, 100D))
        .addEdge(Arc.of(1, 2, 100D))
        .addEdge(Arc.of(2, 0, 100D))
        .build();

    assertThat(g.getNodes()).hasSize(3);
    assertThat(g.getEdges()).hasSize(3);
    assertThat(g.getOutgoingEdges(Node.of(0))).hasSize(1);
    assertThat(g.getOutgoingEdges(Node.of(1))).hasSize(1);
    assertThat(g.getOutgoingEdges(Node.of(2))).hasSize(1);
    g.getEdges().forEach(arc -> assertThat(arc.getWeight()).hasValue(100D));
  }
}
