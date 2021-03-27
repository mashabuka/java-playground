package com.java.playground.leetcode.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @see <a href="https://leetcode.com/problems/subdomain-visit-count/">Subdomain visit count</a>
 */
public final class SubdomainVisitCount {
  private final Map<String, Integer> counts = new HashMap<>();

  public List<String> subdomainVisits(String[] cpdomains) {
    for (String cpdomain : cpdomains) {
      String[] parts = cpdomain.split(" ");
      count(parts[1], Integer.parseInt(parts[0]));
    }

    return counts.entrySet()
        .stream()
        .map(entry -> entry.getValue() + " " + entry.getKey())
        .collect(Collectors.toList());
  }

  private void count(String domain, int visits) {
    String subdomain = domain;
    while (!subdomain.isEmpty()) {
      int total = counts.getOrDefault(subdomain, 0);
      counts.put(subdomain, total + visits);

      int index = subdomain.indexOf('.');
      if (index == -1) {
        break;
      } else {
        subdomain = subdomain.substring(index + 1);
      }
    }
  }
}
