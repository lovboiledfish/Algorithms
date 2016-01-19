package solutions;

import solutions.utils.ExpressionTreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class ExpressionTreeBuild {
    // operator precedences
    private int _getPrec(String a, Integer base) {
        if (a.equals("+") || a.equals("-"))
            return 1 + base;
        if (a.equals("*") || a.equals("/"))
            return 2 + base;
        if (a.equals(""))
            return Integer.MIN_VALUE;

        return Integer.MAX_VALUE;
    }

    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        if (expression == null || expression.length == 0)
            return null;

        Map<ExpressionTreeNode, Integer> prec = new HashMap<ExpressionTreeNode, Integer>();
        Stack<ExpressionTreeNode> branches = new Stack<ExpressionTreeNode>();
        int base = 0;
        for(int i = 0; i <= expression.length; ++i) {
            String exp = (i < expression.length) ? expression[i] : "";
            if ("(".equals(exp)) {
                base += 10;
                continue;
            } else if (")".equals(exp)) {
                base -= 10;
                continue;
            }

            ExpressionTreeNode right = new ExpressionTreeNode(exp);
            int rightPrec = _getPrec(exp, base);
            prec.put(right, rightPrec);
            while (!branches.isEmpty()) {
                if (prec.get(branches.peek()) >= rightPrec) {
                    ExpressionTreeNode subtree = branches.pop();

                    if (branches.isEmpty()) {
                        right.left = subtree;
                    } else {
                        ExpressionTreeNode left = branches.peek();
                        if (prec.get(left) >= rightPrec) {
                            left.right = subtree;
                        } else {
                            right.left = subtree;
                        }
                    }
                } else
                    break;
            }
            branches.push(right);
        }
        return branches.pop().left;
    }
}
