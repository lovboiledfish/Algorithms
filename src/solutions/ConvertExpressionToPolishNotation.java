package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/16/16.
 */
public class ConvertExpressionToPolishNotation {
    // operator precedences
    static private Map<String, Integer> _prec = new HashMap<String, Integer>() {{
        put("+", 0);
        put("-", 0);
        put("*", 1);
        put("/", 1);
        put("(", -1); // stack all other operators
    }};

    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public ArrayList<String> convertToPN(String[] expression) {
        Stack<String> ops = new Stack<String>();
        Stack<ArrayList<String>> exps = new Stack<ArrayList<String>>();

        for (int i = 0; i < expression.length; ++i) {
            switch (expression[i]) {
                case "(":
                    ops.push(expression[i]);
                    break;
                case ")":
                    while (!ops.peek().equals("("))
                        exps.push(_produce(ops, exps));
                    ops.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    if (!ops.isEmpty()) {
                        if (_prec.get(expression[i]).equals(_prec.get(ops.peek()))) {
                            exps.push(_produce(ops, exps));
                            ops.push(expression[i]);
                        } else if (_prec.get(expression[i]) > _prec.get(ops.peek())) {
                            ops.push(expression[i]);
                        } else if (_prec.get(expression[i]) < _prec.get(ops.peek())) {
                            exps.push(_produce(ops, exps));
                            --i;
                        }
                    } else {
                        ops.push(expression[i]);
                    }
                    break;
                default:
                    exps.push(new ArrayList<String>(Collections.singletonList(expression[i])));
                    break;
            }
        }
        while (!ops.empty())
            exps.push(_produce(ops, exps));
        return exps.isEmpty() ? new ArrayList<String>() : exps.pop();
    }

    private ArrayList<String> _produce(Stack<String> ops, Stack<ArrayList<String>> exps) {
        ArrayList<String> exp = new ArrayList<String>();
        ArrayList<String> subExp2 = exps.pop();
        ArrayList<String> subExp1 = exps.pop();
        exp.add(ops.pop());
        exp.addAll(subExp1);
        exp.addAll(subExp2);
        return exp;
    }

    static public class Test {
        static private ConvertExpressionToPolishNotation _solution = new ConvertExpressionToPolishNotation();

        static public void test(String[] expression) {
            _solution.convertToPN(expression).forEach(System.out::println);
        }

        static public void randomTest() {
            test(new String[]{"(","999","/","3","/","3","/","3",")","+","(","1","+","9","/","3",")"});
        }
    }
}
