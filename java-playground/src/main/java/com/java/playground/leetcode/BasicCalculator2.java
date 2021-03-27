package com.retiman.interview.leetcode;

import java.util.Stack;


// Must support +-*/ but no parentheses.  Integer division truncates to 0.
//
// https://leetcode.com/problems/basic-calculator-ii/
public final class BasicCalculator2 {
  public int calculate(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    // Need a stack to delay the + and / operations for later.
    Stack<Integer> stack = new Stack<>();
    int current = 0;
    char operation = '+';

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // Collect digits into a current number, multiplying the previous value by 10 so we can support multidigit
      // numbers.
      if (Character.isDigit(c)) {
        current = (current * 10) + Character.getNumericValue(c);
      }

      if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == s.length() - 1) {
        switch (operation) {
          case ' ':
            break;
          case '-':
            stack.push(-current);
            break;
          case '+':
            stack.push(current);
            break;
          case '*':
            stack.push(stack.pop() * current);
            break;
          case '/':
            stack.push(stack.pop() / current);
            break;
          default:
            throw new RuntimeException();
        }

        // save the operation, for the NEXT number.  for example *3 means we will take the 3 and do a * with it based
        // on whatever is at the top of the stack.
        //
        // likewise -3 means to save the -, read a 3, and push -3 onto the stack for evaluation later.
        operation = c;
        current = 0;
      }
    }

    // Stack should just contain numbers that can be added together (no subtraction because we turned subtraction into
    // negative numbers on the stack).
    int result = 0;
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }
}
