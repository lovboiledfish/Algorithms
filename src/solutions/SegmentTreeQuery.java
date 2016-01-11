package solutions;

import solutions.utils.SegmentTreeNode;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class SegmentTreeQuery {
    /**
     *@param root, start, end: The root of segment tree and
     *                         an segment / interval
     *@return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || start < root.start || end > root.end)
            return Integer.MIN_VALUE;
        if (start == root.start && end == root.end)
            return root.max;
        int mid = (root.start + root.end) >> 1;
        if (end <= mid)
            return query(root.left, start, end);
        else if (start > mid)
            return query(root.right, start, end);
        else
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1, end));
    }

    static public class Test {
        static private SegmentTreeQuery _query = new SegmentTreeQuery();
        static private SegmentTreeBuild _build = new SegmentTreeBuild();

        static public void randomTest() {
            SegmentTreeNode tree = _build.build(0, 3);
            tree.max = 4;
            tree.left.max = 4;
            tree.right.max = 3;
            tree.left.left.max = 1;
            tree.left.right.max = 4;
            tree.right.left.max = 2;
            tree.right.right.max = 3;
            System.out.println(_query.query(tree, 1, 2));
        }
    }
}
