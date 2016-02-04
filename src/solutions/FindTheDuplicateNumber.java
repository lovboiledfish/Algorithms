package solutions;

/**
 * Created by PPlovboiledfish on 2/4/16.
 */
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    ++count;
                }
            }
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
