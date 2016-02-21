package solutions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/20/16.
 */
public class ThreeSum {
	/**
	 * @param numbers : Give an array numbers of n integer
	 * @return : Find all unique triplets in the array which gives the sum of zero.
	 */
	public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		if (numbers != null && numbers.length >= 3) {
			Arrays.sort(numbers);
			int lastTarget = numbers[0] - 1;
			for (int i = 0; i < numbers.length - 2; ++i) {
				while (i < numbers.length - 2 && lastTarget == -numbers[i]) {
					++i;
				}
				int target = -numbers[i];
				lastTarget = target;
				int lo = i + 1, hi = numbers.length - 1;
				while (lo < hi) {
					int sum = numbers[lo] + numbers[hi];
					if (sum == target) {
						ret.add(new ArrayList<>(Arrays.asList(numbers[i], numbers[lo], numbers[hi])));
					}
					if (sum < target) {
						do {
							++lo;
						} while (lo < hi && numbers[lo - 1] == numbers[lo]);
					} else {
						do {
							--hi;
						} while (lo < hi && numbers[hi + 1] == numbers[hi]);
					}
				}
			}
		}
		return ret;
	}
}
