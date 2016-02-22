package solutions;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class RemoveDuplicatesFromSortedArray {
	/**
	 * @param nums: a array of integers
	 * @return : return an integer
	 */
	public int removeDuplicates(int[] nums) {
		int wptr = 0;
		for (int i = 0; i < nums.length; ++i) {
			if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
				nums[wptr++] = nums[i];
			}
		}
		return wptr;
	}
}
