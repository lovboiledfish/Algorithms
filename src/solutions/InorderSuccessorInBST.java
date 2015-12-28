package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 12/27/15.
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root, prev = null;
        while (cur != null && cur.val != p.val) {
            if (p.val < cur.val) {
                prev = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur != null && cur.right != null) {
            TreeNode ptr = cur.right;
            prev = null;
            while (ptr != null) {
                prev = ptr;
                ptr = ptr.left;
            }
        }
        return prev;
    }

    static public class Test {
        static private InorderSuccessorInBST _solution = new InorderSuccessorInBST();

        static public void test(TreeNode root, TreeNode p) {
            TreeNode successor = _solution.inorderSuccessor(root, p);
            if (successor == null)
                System.out.println("NULL");
            else
                System.out.println(successor.val);
        }

        static public void randomTest() {
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(3);
            root.right = new TreeNode(8);
            root.left.left = new TreeNode(1);
            root.left.right = new TreeNode(4);
            root.right.left = new TreeNode(7);
            root.right.right = new TreeNode(9);
            BinaryTreePrinter.printNode(root);
            test(root, new TreeNode(1));
        }
    }
}
