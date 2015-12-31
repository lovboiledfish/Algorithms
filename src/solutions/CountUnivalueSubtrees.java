package solutions;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 12/30/15.
 */
public class CountUnivalueSubtrees {
    static private int _count;

    public int countUnivalSubtrees(TreeNode root) {
        _count = 0;
        _helper(root);
        return _count;
    }

    private boolean _helper(TreeNode root) {
        if (root == null) return true;
        boolean left = _helper(root.left);
        boolean right = _helper(root.right);
        boolean merge = left & right;
        if (merge) {
            if ((root.left != null && root.val != root.left.val) || (root.right != null && root.val != root.right.val))
                merge = false;
            else
                _count += 1;
        }
        return merge;
    }
}
