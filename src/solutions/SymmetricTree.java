package solutions;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 1/24/16.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null)
            return right == null;
        if (right == null)
            return left == null;
        return (isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left) && left.val == right.val);
    }
}
