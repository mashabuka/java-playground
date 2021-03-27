package com.java.playground.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>Generates all letter combinations that can be created with a phone number.</p>
 *
 * @see <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/">Letter combinations of a phone number</a>
 */
public final class LetterCombinationsOfPhoneNumber {
  private final Map<Character, List<String>> map;
  private final Map<String, List<String>> memo;

  public LetterCombinationsOfPhoneNumber() {
    memo = new HashMap<>();
    map = new HashMap<>();

    map.put('0', Arrays.asList(" "));
    map.put('1', Arrays.asList());
    map.put('2', Arrays.asList("a", "b", "c"));
    map.put('3', Arrays.asList("d", "e", "f"));
    map.put('4', Arrays.asList("g", "h", "i"));
    map.put('5', Arrays.asList("j", "k", "l"));
    map.put('6', Arrays.asList("m", "n", "o"));
    map.put('7', Arrays.asList("p", "q", "r", "s"));
    map.put('8', Arrays.asList("t", "u", "v"));
    map.put('9', Arrays.asList("w", "x", "y", "z"));
  }

  // Remove the first digit.  Take the rest of the string, and generate all combinations of words using the remainder
  // of the string.  Then, find the letters mapping to the first digit, and add them in front of all combinations.
  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) {
      return Arrays.asList();
    }

    if (digits.length() == 1) {
      return map.get(digits.charAt(0));
    }

    if (memo.containsKey(digits)) {
      return memo.get(digits);
    }

    char head = digits.charAt(0);
    String rest = digits.substring(1);
    List<String> combinations = letterCombinations(rest);
    List<String> result = new LinkedList<>();

    for (String letter : map.get(head)) {
      List<String> words = combinations.stream()
          .map(word -> letter + word)
          .collect(Collectors.toList());
      result.addAll(words);
    }

    memo.put(digits, result);
    return result;
  }
}
