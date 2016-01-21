package solutions;

/**
 * Created by PPlovboiledfish on 1/20/16.
 */
public class MaximumProductSubarray {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[][] products = new int[2][2];

        if (nums[0] > 0) {
            products[0][0] = nums[0];
        } else {
            products[1][0] = nums[0];
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                products[0][i & 1] = Math.max(products[0][(i - 1) & 1], 1) * nums[i];
                products[1][i & 1] = products[1][(i - 1) & 1] * nums[i];
            } else {
                products[0][i & 1] = products[1][(i - 1) & 1] * nums[i];
                products[1][i & 1] = Math.max(products[0][(i - 1) & 1], 1) * nums[i];
            }
            max = Math.max(products[0][i & 1], max);
        }
        return max;
    }
}
