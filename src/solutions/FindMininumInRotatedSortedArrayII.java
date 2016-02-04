package solutions;

/**
 * Created by PPlovboiledfish on 2/4/16.
 */
public class FindMininumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            }
            else if (nums[mid] < nums[hi]) {
                hi = mid;
            }
            else { // when num[mid] and num[hi] are same
                hi--;
            }
        }
        return nums[lo];
    }
}
