package solutions;

import solutions.utils.SegmentTreeNode;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class SegmentTreeModify {
    /**
     * @param root, index, value: The root of segment tree and
     * @ change the node's value with [index, index] to the new given value
     * @return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        if (root == null || index < root.start || index > root.end)
            return;
        if (root.start == index && root.end == index) {
            root.max = value;
        } else {
            modify(root.left, index, value);
            modify(root.right, index, value);
            root.max = Math.max((root.left != null) ? root.left.max : Integer.MIN_VALUE,
                    (root.right != null) ? root.right.max : Integer.MIN_VALUE);
        }
    }
}
