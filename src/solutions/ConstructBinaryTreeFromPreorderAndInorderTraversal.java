package solutions;

import java.util.HashMap;
import java.util.Map;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;

        final int n = preorder.length;
        Map<Integer, Integer> inorderIdx = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            inorderIdx.put(inorder[i], i);
        }
        return _buildTree(preorder, 0, 0, n, inorderIdx);
    }

    private TreeNode _buildTree(int[] preorder,
                                int pStart,
                                int iStart,
                                int size,
                                Map<Integer, Integer> inorderIdx) {
        if (size <= 0) {
            return null;
        }

        int val = preorder[pStart];
        TreeNode node = new TreeNode(val);
        int leftSize = inorderIdx.get(val) - iStart;
        node.left = _buildTree(preorder, pStart + 1, iStart, leftSize, inorderIdx);
        node.right = _buildTree(preorder, pStart + 1 + leftSize, iStart + leftSize + 1, size - leftSize - 1, inorderIdx);
        return node;
    }
}
