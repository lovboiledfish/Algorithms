package solutions;

import solutions.utils.SegmentTreeNode;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class SegmentTreeBuild {
    /**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null;
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start < end) {
            int mid = (start + end) >> 1;
            root.left = build(start, mid);
            root.right = build(mid + 1, end);
        }
        return root;
    }
}
