package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // post order traversal with stack
        Stack<TreeNode> path = new Stack<>();
        TreeNode ptr = root;
        boolean found = false;
        do {
            while (ptr != null) {
                path.push(ptr);
                ptr = ptr.left;
            }
            TreeNode last = null;
            while (!path.isEmpty()) {
                ptr = path.peek();
                if (ptr == p || ptr == q) {
                    found = true;
                    break;
                }
                if (ptr.right != last) {
                    ptr = ptr.right;
                    break;
                } else {
                    last = path.pop();
                }
            }
        } while (!found && !path.isEmpty());

        // traverse path nodes -- LCA should be among them
        TreeNode target = ptr == p ? q : p;
        while (!path.isEmpty()) {
            TreeNode tree = path.pop();
            if (_find(tree, target)) {
                return tree;
            }
        }
        return null;
    }

    private boolean _find(TreeNode root, TreeNode target) {
        return root == target || root != null && (_find(root.left, target) || _find(root.right, target));
    }

    static public class Test {
        static private LowestCommonAncestorOfABinaryTree _solution = new LowestCommonAncestorOfABinaryTree();

        static public void test(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode LCA = _solution.lowestCommonAncestor(root, p, q);
            BinaryTreePrinter.printNode(LCA);
        }

        static public void randomTest() {
            TreeNode root = new TreeNode(3);
            root.left = new TreeNode(5);
            root.left.left = new TreeNode(6);
            root.left.right = new TreeNode(2);
            root.left.right.left = new TreeNode(7);
            root.left.right.right = new TreeNode(4);
            root.right = new TreeNode(1);
            root.right.left = new TreeNode(0);
            root.right.right = new TreeNode(8);
            BinaryTreePrinter.printNode(root);
            test(root, root.right, root.left.right.right);
        }
    }
}
