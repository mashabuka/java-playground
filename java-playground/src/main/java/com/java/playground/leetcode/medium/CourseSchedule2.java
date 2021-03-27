package com.java.playground.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 * <p>
 * Same as {@code CourseSchedule1} but instead of detecting a cycle, print out the actual sorted order.  Again, Kahn's
 * algorithm can be used.
 * </p>
 *
 * <p>
 * First process a node that is a leaf (one that has no dependencies).  There must be at least one leaf or else the
 * courses can't be completed.  Process this node by removing outgoing edges from that node, and then removing the node.
 * </p>
 *
 * <p>
 * Next, after removing that leaf, some other node or nodes have now become leaf nodes.  Continue processing those nodes
 * until no more leafs exist.
 * </p>
 *
 * <p>
 * Now, one of two things must be true: all nodes are processsed and all edges removed (so now you have a topological
 * sort) OR there are some unprocessed nodes remaining (which means there is a cycle) OR some edges remain (which means
 * there is a cycle).
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/course-schedule-ii/">Course schedule 2</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Topological sorting</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm">Kahn's algorithm</a>
 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-first search</a>
 */
public final class CourseSchedule2 {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    // Set of leafs, or sources without any incoming nodes.  These nodes are safe to process/delete.
    Set<Integer> leafs = new HashSet<>();
    // Map of node to indegrees (number of incoming edges/arcs) of that node.
    Map<Integer, Integer> indegs = new HashMap<>();
    // Map of node to outgoing neighbor nodes (sinks).
    Map<Integer, Set<Integer>> sinkmap = new HashMap<>();

    // Setup the potential leaf nodes.
    for (int i = 0; i < numCourses; i++) {
      leafs.add(i);
    }

    // Prerequisites here are edges like [0,1], [2,0], [3,0], etc.  Set up the graph so we have leafs, incoming, etc.
    for (int i = 0; i < prerequisites.length; i++) {
      // A prerequisite of [1,0] means to take course 1, you need to finish course 0.  So course 0 should have an
      // arrow coming out to go to course 1.  That means course 0 is the sink.
      int[] arc = prerequisites[i];
      int source = arc[0];
      int sink = arc[1];

      Set<Integer> sinks = sinkmap.get(source);
      if (sinks == null) {
        sinks = new HashSet<>();
        sinkmap.put(source, sinks);
      }
      sinks.add(sink);

      int degs = indegs.getOrDefault(sink, 0);
      indegs.put(sink, degs + 1);

      // Leafs have to be all the way to the left in the sorting, with only arrows going *out*.  Anything with arrows
      // going in cannot be a leaf.
      leafs.remove(sink);
    }

    // No leafs, so this just isn't possible to take the courses; there's a cycle for sure.
    if (leafs.isEmpty()) {
      return new int[] {};
    }

    // Start removing edges; if we remove every edge we are golden.
    LinkedList<Integer> sorted = new LinkedList<>();
    int removed = 0;
    while (!leafs.isEmpty()) {
      // All leaf nodes must be sources, so find the sinks to these leaf nodes and start removing them.
      int source = leafs.stream().findFirst().get();
      leafs.remove(source);
      sorted.addFirst(source);

      Set<Integer> sinks = sinkmap.get(source);
      if (sinks == null) {
        continue;
      }

      Set<Integer> removable = new HashSet<>();
      for (int sink : sinks) {
        removable.add(sink);
        removed++;

        // This information will be slightly out of date before the arcs get removed from the set of sinks, but its ok
        // we aren't recursing or using information about the sinks.  We're just gathering all the sinks up, then
        // we are removing all of them anyways.
        int degs = indegs.get(sink);
        degs--;
        indegs.put(sink, indegs.get(sink) - 1);

        // Once we remove the edge from the graph, if no more edges are coming into this sink, the sink has become a
        // source, so we should add it to the leafs.
        if (degs == 0) {
          leafs.add(sink);
        }
      }

      sinks.removeAll(removable);
    }

    // Here if some edges haven't been removed we have a cycle.
    int totalEdges = prerequisites.length;
    if (totalEdges != removed) {
      return new int[] {};
    }

    return sorted.stream().mapToInt(i -> i).toArray();
  }
}
