package solutions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/20/16.
 */
public class FourSum {
	/**
	 * @param numbers : Give an array numbersbers of n integer
	 * @param target : you need to find four elements that's sum of target
	 * @return : Find all unique quadruplets in the array which gives the sum of
	 *           zero.
	 */
	public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
		ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(numbers);

		for (int i = 0; i < numbers.length - 3; i++) {
			if (i != 0 && numbers[i] == numbers[i - 1]) {
				continue;
			}

			for (int j = i + 1; j < numbers.length - 2; j++) {
				if (j != i + 1 && numbers[j] == numbers[j - 1])
					continue;

				int left = j + 1;
				int right = numbers.length - 1;
				while (left < right) {
					int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
					if (sum < target) {
						left++;
					} else if (sum > target) {
						right--;
					} else {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(numbers[i]);
						tmp.add(numbers[j]);
						tmp.add(numbers[left]);
						tmp.add(numbers[right]);
						rst.add(tmp);
						left++;
						right--;
						while (left < right && numbers[left] == numbers[left - 1]) {
							left++;
						}
						while (left < right && numbers[right] == numbers[right + 1]) {
							right--;
						}
					}
				}
			}
		}

		return rst;
	}

	static public class Test {
		static private FourSum _solution = new FourSum();

		static public void test(int[] numbers, int target) {
			_solution.fourSum(numbers, target).forEach(list -> {
				list.stream().map(num -> num + ",").forEach(System.out::println);
				System.out.println();
			});
		}

		static public void randomTest() {
			int[] numbers = {1,0,-1,-1,-1,-1,0,1,1,1,2};
			int target = 2;
			test(numbers, target);
		}
	}
}
