package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || k > nums.length || k <= 0)
            return -1;
        return _findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int _findKthLargest(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
        int wptr = start;
        for (int i = start; i < end; ++i) {
            if (nums[i] > pivot)
                _swp(nums, wptr++, i);
        }
        if (k == wptr - start + 1) return pivot;
        _swp(nums, wptr, end);
        if (wptr - start + 1 > k) return _findKthLargest(nums, start, wptr - 1, k);
        else return _findKthLargest(nums, wptr + 1, end, k - (wptr - start + 1));
    }

    private void _swp(int[] nums, int left, int right) {
        if (left == right) return;
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    static public class Test {
        static private KthLargestElementInAnArray _solution = new KthLargestElementInAnArray();

        static public void test(int[] nums, int k, int ans) {
            Assert.check(ans == _solution.findKthLargest(nums, k));
        }

        static public void randomTest() {
            test(new int[] {3,2,1,5,6,4}, 2, 5);
        }
    }
}
