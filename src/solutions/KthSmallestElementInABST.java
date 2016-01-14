package solutions;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 1/13/16.
 */
public class KthSmallestElementInABST {
    private int _count;
    private int _kth;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return -1;
        _count = k;
        _kth = -1;
        _DFS(root);
        return _kth;
    }

    private boolean _DFS(TreeNode root) {
        if (root.left != null && _DFS(root.left))
            return true;

        --_count;
        if (_count == 0) {
            _kth = root.val;
            return true;
        }

        return root.right != null && _DFS(root.right);
    }
}
