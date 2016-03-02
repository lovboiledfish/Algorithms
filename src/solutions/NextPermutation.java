package solutions;

/**
 * Created by feiyi.zhan on 3/2/16.
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		for (; i >= 0; --i) {
			if (nums[i + 1] > nums[i]) {
				break;
			}
		}
		if (i == -1) {
			_reverse(nums, 0, nums.length - 1);
		} else {
			// binary search for the smallest num larger than nums[i]
			// in range [i+1 ... end]
			int lo = i + 1, hi = nums.length - 1, pos = i + 1;
			while (lo <= hi) {
				int mid = lo + ((hi - lo) >> 1);
				if (nums[i] < nums[mid]) {
					pos = mid;
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			_swp(nums, i, pos);
			_reverse(nums, i + 1, nums.length - 1);
		}
	}

	// start - inclusive
	// end - inclusive
	private void _reverse(int[] arr, int start, int end) {
		while (start < end) {
			_swp(arr, start++, end--);
		}
	}

	private void _swp(int[] arr, int i, int j) {
		if (i != j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
}
