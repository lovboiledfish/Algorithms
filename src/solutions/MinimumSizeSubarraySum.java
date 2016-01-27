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
        int minLength = Integer.MAX_VALUE;
        if (nums != null && nums.length > 0) {
            int sum = 0;
            int slow = 0;
            for (int fast = 0; fast < nums.length; ++fast) {
                sum += nums[fast];
                if (sum >= s) {
                    for(; slow <= fast && sum >= s; ++slow) {
                        sum -= nums[slow];
                    }
                    minLength = Math.min(minLength, fast - slow + 2);
                }
            }
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
