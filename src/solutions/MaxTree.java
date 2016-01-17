package solutions;

import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 1/16/16.
 */
public class MaxTree {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        TreeNode maxTree = null;
        if (A != null && A.length > 0) {
            for (int a : A) {
                if (maxTree == null) {
                    maxTree = new TreeNode(a);
                } else {
                    if (a > maxTree.val) {
                        TreeNode top = new TreeNode(a);
                        top.left = maxTree;
                        maxTree = top;
                    } else {
                        TreeNode ptr = maxTree;
                        while (ptr.right != null && ptr.right.val > a)
                            ptr = ptr.right;
                        TreeNode right = ptr.right;
                        TreeNode insert = new TreeNode(a);
                        ptr.right = insert;
                        insert.left = right;
                    }
                }
            }
        }
        return maxTree;
    }
}
