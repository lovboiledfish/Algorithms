package solutions;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/29/16.
 */
public class ConvertExpressionToReversePolishNotation {
    class Node {
        public int prec;
        public String expr;
        public Node left;
        public Node right;

        public Node(int p, String e) {
            prec = p;
            expr = e;
        }
    }

    class ExpressionTree {
        // operator precedences
        private int _getPrec(String a, Integer base) {
            if (a.equals("+") || a.equals("-"))
                return 1 + base;
            if (a.equals("*") || a.equals("/"))
                return 2 + base;
            if (a.equals("("))
                return base + 10;
            if (a.equals(")"))
                return base - 10;
            if (a.equals(""))
                return Integer.MIN_VALUE;

            return Integer.MAX_VALUE;
        }

        private Node _root;

        public ExpressionTree(String[] expressions) {
            if (expressions == null || expressions.length == 0)
                return;

            int basePrec = 0;
            Stack<Node> branches = new Stack<Node>();
            for (int i = 0; i <= expressions.length; ++i) {
                String e = (i < expressions.length) ? expressions[i] : "";
                if ("(".equals(e)) {
                    basePrec = _getPrec(e, basePrec);
                    continue;
                }
                if (")".equals(e)) {
                    basePrec = _getPrec(e, basePrec);
                    continue;
                }

                int prec = _getPrec(e, basePrec);
                Node right = new Node(prec, e);
                while (!branches.isEmpty() && branches.peek().prec >= prec) {
                    Node subtree = branches.pop();
                    if (branches.isEmpty()) {
                        right.left = subtree;
                    } else {
                        if (branches.peek().prec < prec) {
                            right.left = subtree;
                        } else {
                            branches.peek().right = subtree;
                        }
                    }
                }
                branches.push(right);
            }
            _root = branches.pop().left;
        }

        public Node getExpressionTree() {
            return _root;
        }
    }

    /**
     * @param expressions: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expressions) {
        Node et = new ExpressionTree(expressions).getExpressionTree();
        ArrayList<String> reversePolish = new ArrayList<String>();
        _DFS(et, reversePolish);
        return reversePolish;
    }

    private void _DFS(Node root, ArrayList<String> postorder) {
        if (root == null)
            return;
        _DFS(root.left, postorder);
        _DFS(root.right, postorder);
        postorder.add(root.expr);
    }
}
