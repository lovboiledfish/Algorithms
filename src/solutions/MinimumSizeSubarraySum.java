package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class MinimumSizeSubarraySum {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length < 1)
            return -1;

        int l = 0, r = 0, sum = nums[0];
        int minSize = Integer.MAX_VALUE;
        while (l < nums.length && r < nums.length) {
            if (sum >= s)
                minSize = Math.min(minSize, r - l + 1);

            if (sum <= s) {
                ++r;
                if (r < nums.length)
                    sum += nums[r];
            } else {
                sum -= nums[l++];
                if (l > r)
                    r = l;
            }
        }
        return minSize > nums.length ? -1 : minSize;
    }
}
