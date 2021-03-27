package com.java.playground.leetcode;

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

  public LetterCombinationsOfPhoneNumber() {
    map = new HashMap<>();

    map.put('0', List.of(" "));
    map.put('1', List.of());
    map.put('2', List.of("a", "b", "c"));
    map.put('3', List.of("d", "e", "f"));
    map.put('4', List.of("g", "h", "i"));
    map.put('5', List.of("j", "k", "l"));
    map.put('6', List.of("m", "n", "o"));
    map.put('7', List.of("p", "q", "r", "s"));
    map.put('8', List.of("t", "u", "v"));
    map.put('9', List.of("w", "x", "y", "z"));
  }

  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) {
      return List.of();
    }

    if (digits.length() == 1) {
      return map.get(digits.charAt(0));
    }

    // First split the phone number into the first digit, and the rest of the digits.
    char head = digits.charAt(0);
    String rest = digits.substring(1);

    // Generate letter combinations for the result of the digits only, then add all possible letters from the first
    // digit to the combinations generated with the remaining digits.
    List<String> combinations = letterCombinations(rest);

    List<String> result = new LinkedList<>();
    for (String letter : map.get(head)) {
      List<String> words = combinations.stream()
          .map(word -> letter + word)
          .collect(Collectors.toList());
      result.addAll(words);
    }

    return result;
  }
}
