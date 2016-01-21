package solutions;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/20/16.
 */
public class LongestIncreasingSubsequence {
    class BST {
        private int val; // nums[i]
        private int max; // dp[k]
        private BST left;
        private BST right;

        public BST(int v, int m) {
            val = v;
            max = m;
        }

        public int insert(int val) {
            BST ptr = this, prev = null;
            int max = 0;
            Stack<BST> larger = new Stack<BST>();
            while (ptr != null) {
                prev = ptr;
                if (ptr.val == val) {
                    max = Math.max(ptr.max, max);
                    break;
                } else if (ptr.val > val) {
                    larger.push(ptr);
                    ptr = ptr.left;
                } else {
                    max = Math.max(ptr.max, max);
                    ptr = ptr.right;
                }
            }
            ++max;
            if (ptr == null) {
                if (prev.val > val) {
                    prev.left = new BST(val, max);
                } else {
                    prev.right = new BST(val, max);
                }
            } else {
                ptr.max = max;
            }
            while (!larger.isEmpty()) {
                BST node = larger.pop();
                node.max = Math.max(max, node.max);
            }
            return max;
        }
    }

    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length < 1)
            return 0;

        int max = 0;
        BST bst = new BST(Integer.MIN_VALUE, 0);
        for (int num : nums) max = Math.max(max, bst.insert(num));
        return max;
    }
}
