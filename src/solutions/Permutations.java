package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feiyi.zhan on 3/6/16.
 */
public class Permutations {
	/**
	 * @param nums: A list of integers.
	 * @return: A list of permutations.
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums != null && nums.length > 0) {
			int size = nums.length;
			Integer[] arr = new Integer[size];
			int factor = 1;
			for (int i = 0; i < size; ++i) {
				arr[i] = nums[i];
				factor *= (i + 1);
			}
			while (factor > 0) {
				res.add(new ArrayList<>(Arrays.asList(arr)));
				nextPermutation(arr);
				--factor;
			}
		}
		return res;
	}

	public void nextPermutation(Integer[] nums) {
		int i = nums.length - 2;
		for (; i >= 0; --i) {
			if (nums[i + 1] > nums[i]) {
				break;
			}
		}
		if (i == -1) {
			_reverse(nums, 0, nums.length - 1);
		} else {
			int lo = i + 1, hi = nums.length - 1, pos = i + 1;
			while (lo <= hi) {
				int mid = lo + ((hi - lo) >> 1);
				if (nums[i].compareTo(nums[mid]) < 0) {
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

	private void _reverse(Integer[] arr, int start, int end) {
		while (start < end) {
			_swp(arr, start++, end--);
		}
	}

	private void _swp(Integer[] arr, int i, int j) {
		if (i != j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
}
