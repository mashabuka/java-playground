package com.java.playground.leetcode;

import java.util.Stack;


/**
 * <p>
 * The basic calculator must calculate addition and subtraction (not multiplication and division), but support
 * parentheses.  Because of this, all operations have the same precedence (unless parentheses are used).  The input
 * string may contain spaces.
 * </p>
 *
 * @see <a href="https://leetcode.com/problems/basic-calculator/">Basic calculator</a>
 */
public final class BasicCalculator1 {
  public int calculate(String s) {
    Stack<Integer> stack = new Stack<>();
    int current = 0;
    int result = 0;
    int sign = 1;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        // Collect numbers to add to the result.  The numbers could be multidigit.  To account for this, we have to
        // keep track of digits as we go and multiply each intermediate result by 10 as we go along.
        //
        // For example, if we see "9" followed by "8", multiply "9" by 10 getting 98, and then add "8", getting "98".
        // For example, if we see "98" followed by "7", multiply "98" by 10 getting "980", and then add "7", getting
        // "987".
        current = 10 * current + Character.getNumericValue(c);
      } else if (c == '+') {
        // Calculate the addition based on the last seen sign.
        result += sign * current;
        // Set the last seen sign and reset the current value to 0 after the addition or subtraction.
        sign = 1;
        current = 0;
      } else if (c == '-') {
        // Calculate the addition based on the last seen sign.
        result += sign * current;
        // Set the last seen sign and reset the current value to 0 after the addition or subtraction.
        sign = -1;
        current = 0;
      } else if (c == '(') {
        // We expect to see a matching ")" later.  We need to save the intermediate result and the last seen sign, so
        // we know if we are going to eventually add or subtract the value when we see the ")".
        stack.push(result);
        stack.push(sign);
        // Reset the sign and the result as we calculate the value inside the parentheses.
        sign = 1;
        result = 0;
      } else if (c == ')') {
        // We now see a matching ")", so calculate the value of the subexpression "(subexpression)".
        result += sign * current;
        // The first item at the top of the stack should be the sign of the subexpression, so either it is
        // "+(subexpression)" or "-(subexpression)".
        result *= (int) stack.pop();
        // The second item on top of the stack should be the previous result value, which we add to the subexpression
        // value to obtain the total expression value.
        result += (int) stack.pop();
        // Now we result the current number, and keep consuming (for example, we could have just evaluated something
        // like "1+(2+3)+4".  This would mean we have evaluated the "1+(2+3)" and are waiting to consume the remaining
        // "+4".
        current = 0;
      }
    }

    return result + (sign * current);
  }
}
