package solutions;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/30/16.
 */
public class ValidParenthesis {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        if (s == null || (s.length() & 1) == 1)
            return false;

        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            switch(c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
