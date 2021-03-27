package com.java.playground.leetcode.easy;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public final class SubdomainVisitCountTest {
  private final SubdomainVisitCount solution = new SubdomainVisitCount();

  @Test
  public void testSolution() {
    List<String> cpdomains = solution.subdomainVisits(new String[] {
        "9001 mail.google.com",
        "1001 linkedin.com"
    });

    assertThat(cpdomains).containsExactly(
        "10002 com",
        "9001 google.com",
        "9001 mail.google.com",
        "1001 linkedin.com");
  }
}
