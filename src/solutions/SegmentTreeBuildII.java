package solutions;

import solutions.utils.SegmentTreeNode;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class SegmentTreeBuildII {
    /**
     *@param A: a list of integer
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        if (A == null || A.length == 0)
            return null;
        return _build(A, 0, A.length - 1);
    }

    private SegmentTreeNode _build(int[] A, int start, int end) {
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        if (start < end) {
            int mid = (start + end) >> 1;
            node.left = _build(A, start, mid);
            node.right = _build(A, mid + 1, end);
            node.max = Math.max(node.left.max, node.right.max);
        }
        return node;
    }
}
