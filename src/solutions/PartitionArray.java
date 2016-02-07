package solutions;

/**
 * Created by PPlovboiledfish on 2/7/16.
 */
public class PartitionArray {
    /**
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int wptr = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < k) {
                int tmp = nums[wptr];
                nums[wptr] = nums[i];
                nums[i] = tmp;
                ++wptr;
            }
        }
        return wptr;
    }
}
