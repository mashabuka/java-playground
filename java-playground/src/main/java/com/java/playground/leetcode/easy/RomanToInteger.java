package com.java.playground.leetcode.easy;

import java.util.HashSet;
import java.util.Set;


/**
 * @see <a href="https://leetcode.com/problems/roman-to-integer/">Roman to integer</a>
 */
public final class RomanToInteger {
  private final Set<Character> cs = new HashSet<>();

  public RomanToInteger() {
    cs.add('V');
    cs.add('L');
    cs.add('D');
    cs.add('M');
  }

  public int romanToInt(String s) {
    int total = 0;

    for (int i = 0; i < s.length(); i++) {
      char c1 = s.charAt(i);
      int v1 = getValue(c1);
      if (i == s.length() - 1 || cs.contains(c1)) {
        total += v1;
        continue;
      }

      char c2 = s.charAt(i + 1);
      int v2 = getValue(c2);
      if ((c1 == 'I' && c2 == 'V')
        || (c1 == 'I' && c2 == 'X')
        || (c1 == 'X' && c2 == 'L')
        || (c1 == 'X' && c2 == 'C')
        || (c1 == 'C' && c2 == 'D')
        || (c1 == 'C' && c2 == 'M')) {
        total += (v2 - v1);
        i++;
      } else {
        total += v1;
      }
    }

    return total;
  }

  private int getValue(char c) {
    if (c == 'V') {
      return 5;
    } else if (c == 'L') {
      return 50;
    } else if (c == 'D') {
      return 500;
    } else if (c == 'M') {
      return 1000;
    } else if (c == 'I') {
      return 1;
    } else if (c == 'X') {
      return 10;
    } else if (c == 'C') {
      return 100;
    }

    throw new RuntimeException("Not a roman numeral");
  }
}
