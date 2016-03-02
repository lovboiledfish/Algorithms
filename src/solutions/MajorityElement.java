package solutions;

import java.util.ArrayList;

/**
 * Created by feiyi.zhan on 3/2/16.
 */
public class MajorityElement {
	/**
	 * @param nums: a list of integers
	 * @return: find a  majority number
	 */
	public int majorityNumber(ArrayList<Integer> nums) {
		int num = -1;
		int cnt = 0;
		for (int n : nums) {
			if (n == num) {
				++cnt;
			} else {
				--cnt;
				if (cnt <= 0) {
					num = n;
					cnt = 1;
				}
			}
		}
		return num;
	}
}
