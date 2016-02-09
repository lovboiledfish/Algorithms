package solutions;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        int height = _getHeight(root);
        if (height == 0) {
            return 0;
        }

        int left = 0, right = (1 << (height - 1)) - 1;
        TreeNode ptr = root;
        while (left < right && ptr != null) {
            int mid = (left + right) >> 1;
            int leftHeight = _getHeight(ptr.left);
            int rightHeight = _getHeight(ptr.right);
            if (leftHeight > rightHeight) {
                right = mid;
                ptr = ptr.left;
            } else {
                left = mid + 1;
                ptr = ptr.right;
            }
        }
        return (1 << (height - 1)) + right;
    }

    private int _getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            ++height;
            root = root.left;
        }
        return height;
    }
}
