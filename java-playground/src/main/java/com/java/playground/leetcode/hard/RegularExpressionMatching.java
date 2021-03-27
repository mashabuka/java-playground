package com.java.playground.leetcode.hard;


/**
 * <p>
 * Implements a simple, limited regular expression that supports '.' and '*'.  Parentheses are not allowed.  Note that
 * patterns like "a*b*c*d*" are supported, which could match "abcd", "abc", or just "".
 * </p>
 *
 * <p>
 * Once we match on a character (or characters) , we can throw away the part of the text that matched along with the
 * pattern, then reduce to a smaller problem.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/regular-expression-matching/description/">Regular expression matching</a>
 * @see <a href="https://en.wikipedia.org/wiki/Regular_expression">Regular expression</a>
 */
public final class RegularExpressionMatching {
  public boolean isMatch(String text, String pattern) {
    if (pattern.isEmpty()) {
      return text.isEmpty();
    }

    boolean isFirstCharMatch = isFirstCharMatch(text, pattern.charAt(0));
    boolean isGlob = pattern.length() >= 2 && pattern.charAt(1) == '*';

    if (isGlob) {
      boolean isGlobbedNothing = isMatch(text, pattern.substring(2));
      if (isGlobbedNothing) {
        return true;
      }

      //  If we globbed something, let's see if the rest of the characters match.
      boolean isRestCharsMatch = isFirstCharMatch && isMatch(text.substring(1), pattern);
      return isRestCharsMatch;
    }

    // If first chars match, reduce to subproblem of just the text/pattern with first char chopped off.
    return isFirstCharMatch && isMatch(text.substring(1), pattern.substring(1));
  }

  private boolean isFirstCharMatch(String text, char c) {
    if (text.isEmpty()) {
      return false;
    }

    if (c == '.') {
      return true;
    }

    return c == text.charAt(0);
  }
}
