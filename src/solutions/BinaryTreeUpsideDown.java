package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 12/6/15.
 */
public class BinaryTreeUpsideDown {
    private TreeNode _temp;

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null)
            return root;

        _reverseBTHelper(root);
        return _temp;
    }

    private TreeNode _reverseBTHelper(TreeNode root) {
        if (root.left == null) {
            _temp = root;
            return root;
        }

        TreeNode stub = _reverseBTHelper(root.left);
        stub.left = root.right;
        stub.right = root;
        root.left = root.right = null;
        return root;
    }

    static public class Test {
        static private BinaryTreeUpsideDown _solution = new BinaryTreeUpsideDown();

        static public void test(TreeNode root) {
            BinaryTreePrinter.printNode(root);
            TreeNode reversed = _solution.upsideDownBinaryTree(root);
            BinaryTreePrinter.printNode(reversed);
        }
    }
}
