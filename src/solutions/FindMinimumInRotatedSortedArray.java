package solutions;

/**
 * Created by feiyi.zhan on 2/15/16.
 */
public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Integer.MIN_VALUE;
		}
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			if (nums[mid] > nums[high]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return nums[high];
	}
}
