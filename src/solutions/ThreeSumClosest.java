package solutions;

import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/20/16.
 */
public class ThreeSumClosest {
	/**
	 * @param numbers: Give an array numbers of n integer
	 * @param target : An integer
	 * @return : return the sum of the three integers, the sum closest target.
	 */
	public int threeSumClosest(int[] numbers ,int target) {
		int closest = Integer.MAX_VALUE;
		if (numbers != null && numbers.length > 2) {
			Arrays.sort(numbers);
			int i = 0;
			while (i < numbers.length - 2) {
				int curTarget = target - numbers[i];
				int lo = i + 1, hi = numbers.length - 1;
				while (lo < hi) {
					int sum = numbers[lo] + numbers[hi];
					if (sum == curTarget) {
						return target;
					}
					if (Math.abs(sum - curTarget) < Math.abs(closest - target)) {
						closest = sum + numbers[i];
					}
					if (sum > curTarget) {
						--hi;
						while (lo < hi && numbers[hi + 1] == numbers[hi]) {
							--hi;
						}
					} else {
						++lo;
						while (lo < hi && numbers[lo - 1] == numbers[lo]) {
							++lo;
						}
					}
				}
				++i;
				while (i < numbers.length - 2 && numbers[i - 1] == numbers[i]) {
					++i;
				}
			}
		}
		return closest;
	}
}
