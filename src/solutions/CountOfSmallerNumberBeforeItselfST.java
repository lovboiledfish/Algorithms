package solutions;

import java.util.ArrayList;

/**
 * Created by PPlovboiledfish on 1/14/16.
 */
public class CountOfSmallerNumberBeforeItselfST {
    class SegmentTreeNode {
        public int start, end, count;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = this.right = null;
        }
    }

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

    public void modify(SegmentTreeNode root, int index, int value) {
        if (root == null || index < root.start || index > root.end)
            return;
        if (root.start == index && root.end == index) {
            ++root.count;
        } else {
            modify(root.left, index, value);
            modify(root.right, index, value);
            root.count = ((root.left != null) ? root.left.count : 0) + ((root.right != null) ? root.right.count : 0);
        }
    }

    /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is
     *          smaller than it and return count number array
     */
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        ArrayList<Integer> res = new ArrayList<>();
        SegmentTreeNode segTree = build(0, 10000);
        for (int a : A) {
            res.add(query(segTree, 0, a - 1));
            modify(segTree, a, 1);
        }
        return res;
    }
}
