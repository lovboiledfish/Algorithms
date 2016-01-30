package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/29/16.
 */
public class ExpressionEvaluation {
    class TreeNode {
        public int prec;
        public String expr;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int p, String e) {
            prec = p;
            expr = e;
        }
    }

    private int _getPrecedence(String expression, int base) {
        switch (expression) {
            case "+":
            case "-":
                return base + 1;
            case "*":
            case "/":
                return base + 2;
            case "":
                return Integer.MIN_VALUE;
            default:
                return Integer.MAX_VALUE;
        }
    }

    private TreeNode _contructExpressionTree(String[] expressions) {
        if (expressions == null || expressions.length == 0)
            return null;

        Stack<TreeNode> branches = new Stack<TreeNode>();
        int base = 0;
        for (int i = 0; i <= expressions.length; ++i) {
            String expression = (i == expressions.length) ? "" : expressions[i];
            if ("(".equals(expression)) {
                base += 10;
                continue;
            }
            if (")".equals(expression)) {
                base -= 10;
                continue;
            }
            int prec = _getPrecedence(expression, base);
            TreeNode right = new TreeNode(prec, expression);
            while (!branches.isEmpty() && prec <= branches.peek().prec) {
                TreeNode subtree = branches.pop();
                if (branches.isEmpty()) {
                    right.left = subtree;
                } else {
                    if (branches.peek().prec >= prec) {
                        branches.peek().right = subtree;
                    } else {
                        right.left = subtree;
                    }
                }
            }
            branches.push(right);
        }
        return branches.pop().left;
    }

    private int _eval(TreeNode root) {
        if (root == null)
            return 0;
        int left = _eval(root.left);
        int right = _eval(root.right);
        switch (root.expr) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                return Integer.parseInt(root.expr);
        }
    }

    /**
     * @param expressions: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expressions) {
        TreeNode expressionTree = _contructExpressionTree(expressions);
        return _eval(expressionTree);
    }

    static public class Test{
        static private ExpressionEvaluation _solution = new ExpressionEvaluation();

        static public void test(String[] expressions, int ans) {
            Assert.check(_solution.evaluateExpression(expressions) == ans);
        }

        static public void randomTest() {
            String[] expressions = {"(","999","/","3","/","3","/","3",")","+","(","1","+","9","/","3",")"};
            test(expressions, 41);
        }
    }
}
