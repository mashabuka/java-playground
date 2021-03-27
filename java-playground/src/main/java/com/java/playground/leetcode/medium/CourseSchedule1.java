package com.java.playground.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Determines if all courses can be finished given the prerequisites.  Basically boils down to checking if the course
 * list has a cycle in it.  The specific algorithm used is Kahn's algorithm, but depth-first search can also be used.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/course-schedule/">Course schedule</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological sorting</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm">Kahn's algorithm</a>
 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
 */
public final class CourseSchedule1 {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // An empty braph means there are no cycles.
    if (prerequisites.length == 0) {
      return true;
    }

    // The input gives you edges for the course.  so course 0 would have edges (0,1) (0,2) (0,3) etc.  You don't need
    // to loop through the int numCourses to build a graph of all the nodes.
    Map<Integer, Node> graph = new HashMap<>();
    for (int[] prerequisite : prerequisites) {
      int sourceIndex = prerequisite[1];
      Node source = find(graph, sourceIndex);

      int sinkIndex = prerequisite[0];
      Node sink = find(graph, sinkIndex);

      source.adjacent.add(sinkIndex);
      sink.incoming++;
    }

    // Kahn's algorithm is to keep removing leaf nodes from the graph by removing edges going to leaf nodes.  If we
    // keep doing that we'll get an ordering (or extra edges, resulting in a cycle).  This can be done after building
    // the graph.
    List<Integer> leafs = new LinkedList<>();
    for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
      int index = entry.getKey();
      Node node = entry.getValue();
      if (node.incoming == 0) {
        leafs.add(index);
      }
    }

    // Remove edges until we got none and see if any remain.  The sorted list is the topological sort; we don't need
    // it here but if we wanted a topological sort that's how we'd get it.
    List<Integer> sorted = new LinkedList<>();
    int removed = 0;
    while (!leafs.isEmpty()) {
      int leaf = leafs.remove(0);
      sorted.add(leaf);

      // Sinks are prerequisites in our graph
      for (int sinkIndex : graph.get(leaf).adjacent) {
        Node node = graph.get(sinkIndex);
        node.incoming--;
        removed++;

        if (node.incoming == 0) {
          leafs.add(sinkIndex);
        }
      }
    }

    // We removed all edges, so we have a good sort.
    if (removed == prerequisites.length) {
      return true;
    }

    // Edges remained so we have a cycle.
    return false;
  }

  private Node find(Map<Integer, Node> graph, int course) {
    Node node = graph.get(course);
    if (node == null) {
      node = new Node();
      graph.put(course, node);
    }

    return node;
  }

  private static final class Node {
    public int incoming = 0;
    public List<Integer> adjacent = new LinkedList<>();
  }
}
