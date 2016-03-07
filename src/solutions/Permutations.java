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
			Integer[] arr = new Integer[nums.length];
			Arrays.fill(arr, Integer.MIN_VALUE);

			int factor = 1;
			int cnt = nums.length;
			while (cnt > 0) {
				factor *= cnt;
				--cnt;
			}
			for (int i = 0; i < factor; ++i) {
				res.add(new ArrayList<>(Arrays.asList(arr)));
			}

			cnt = nums.length;
			while (cnt > 0) {
				final int num = nums[nums.length - cnt];
				for (int k = 0; k < res.size() / factor; ++k) {
					List<Integer> list = res.get(k * factor);
					int row = 0;
					for (int i = 0; i < nums.length; ++i) {
						if (list.get(i) == Integer.MIN_VALUE) {
							for (int j = 0; j < factor / cnt; ++j, ++row) {
								res.get(k * factor + row).set(i, num);
							}
						}
					}
				}
				factor /= cnt;
				--cnt;
			}
		}
		return res;
	}

	static public class Test {
		static private Permutations _solution = new Permutations();

		static public void test(int[] nums) {
			_solution.permute(nums).forEach(list -> {
				list.forEach(e -> System.out.print(e + ", "));
				System.out.println();
			});
		}

		static public void randomTest() {
			int[] nums = {1, 2, 3};
			test(nums);
		}
	}
}
