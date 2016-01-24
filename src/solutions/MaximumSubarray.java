package solutions;

/**
 * Created by PPlovboiledfish on 1/23/16.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int minSum = 0, sum = 0, maxSubSum = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxSubSum = Math.max(maxSubSum, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return maxSubSum;
    }
}
