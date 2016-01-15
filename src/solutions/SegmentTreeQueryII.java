package solutions;

import solutions.utils.SegmentTreeNode;

/**
 * Created by PPlovboiledfish on 1/14/16.
 */
public class SegmentTreeQueryII {
    /**
     *@param root, start, end: The root of segment tree and
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end)
            return 0;
        start = Math.max(start, root.start);
        end = Math.min(end, root.end);
        if (root.start == start && root.end == end)
            return root.count;
        int mid = (root.start + root.end) >> 1;
        if (end <= mid)
            return query(root.left, start, end);
        else if (start > mid)
            return query(root.right, start, end);
        else
            return query(root.left, start, mid) + query(root.right, mid + 1, end);
    }
}
