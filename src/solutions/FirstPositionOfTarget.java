package solutions;

/**
 * Created by feiyi.zhan on 2/24/16.
 */
public class FirstPositionOfTarget {
	/**
	 * @param nums: The integer array.
	 * @param target: Target to find.
	 * @return: The first position of target. Position starts from 0.
	 */
	public int binarySearch(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		int left = lo - 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (nums[mid] < target) {
				left = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		++left;
		return nums.length > left && nums[left] == target ? left : -1;
	}
}
