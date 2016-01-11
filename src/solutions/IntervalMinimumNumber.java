package solutions;

import solutions.utils.Interval;

import java.util.ArrayList;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class IntervalMinimumNumber {
    public class SegmentTreeNode {
        public int start, end, min;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.left = this.right = null;
        }
    }

    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, ArrayList<Interval> queries) {
        if (A == null || A.length == 0)
            return null;
        SegmentTreeNode sTree = _build(A, 0, A.length - 1);
        ArrayList<Integer> res = new ArrayList<>();
        for (Interval interval : queries)
            res.add(_query(sTree, interval.start, interval.end));
        return res;
    }

    private SegmentTreeNode _build(int[] A, int start, int end) {
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        if (start < end) {
            int mid = (start + end) >> 1;
            node.left = _build(A, start, mid);
            node.right = _build(A, mid + 1, end);
            node.min = Math.min(node.left.min, node.right.min);
        }
        return node;
    }

    private int _query(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end || start < root.start || end > root.end)
            return Integer.MIN_VALUE;
        if (start == root.start && end == root.end)
            return root.min;
        int mid = (root.start + root.end) >> 1;
        if (end <= mid)
            return _query(root.left, start, end);
        else if (start > mid)
            return _query(root.right, start, end);
        else
            return Math.min(_query(root.left, start, mid), _query(root.right, mid + 1, end));
    }
}
