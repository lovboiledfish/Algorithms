package solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PPlovboiledfish on 1/6/16.
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        _DFS(new StringBuilder(), 0, true, 0, num, target, res);
        return res;
    }

    private void _DFS(StringBuilder expression, long eval, boolean add, long multiplier, String num, int target, List<String> res) {
        if (num == null) {
            if (eval + (add? multiplier : -multiplier) == target)
                res.add(expression.toString());
        } else {
            for (int i = 1; i <= num.length(); ++i) {
                String operandStr = num.substring(0, i);
                String nextNum = (i == num.length()) ? null : num.substring(i);
                long operand = Long.parseLong(operandStr);
                int len = expression.length();
                if (len == 0) {
                    _DFS(expression.append(operandStr), eval, true, operand, nextNum, target, res);
                    expression.setLength(len);
                } else {
                    _DFS(expression.append("+").append(operandStr), eval + (add ? multiplier : -multiplier), true, operand, nextNum, target, res);
                    expression.setLength(len);
                    _DFS(expression.append("-").append(operandStr), eval + (add ? multiplier : -multiplier), false, operand, nextNum, target, res);
                    expression.setLength(len);
                    _DFS(expression.append("*").append(operandStr), eval, add, multiplier * operand, nextNum, target, res);
                    expression.setLength(len);
                }
                if (operand == 0) break;
            }
        }
    }

    static public class Test {
        static private ExpressionAddOperators _solution = new ExpressionAddOperators();

        static public void test(String num, int target) {
            _solution.addOperators(num, target).forEach(System.out::println);
        }

        static public void randomTest() {
            test("232", 8);
            test("3456237490", 9191);
        }
    }
}
