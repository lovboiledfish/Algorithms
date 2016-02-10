package solutions;

/**
 * Created by feiyi.zhan on 2/9/16.
 */
public class RangeSumQueryMutable {
	private int[] BIT;
	private int[] numbers;

	public RangeSumQueryMutable(int[] nums) {
		BIT = new int[nums.length + 1];
		numbers = nums;
		for (int i = 0; i < nums.length; ++i) {
			_updateBIT(i, nums[i]);
		}
	}

	public void update(int i, int val) {
		_updateBIT(i, val - numbers[i]);
		numbers[i] = val;
	}

	private void _updateBIT(int i, int delta) {
		++i;
		for (; i < BIT.length; i += (i & -i)) {
			BIT[i] += delta;
		}
	}

	private int _queryBIT(int i) {
		++i;
		int sum = 0;
		for (; i > 0; i -= (i & -i)) {
			sum += BIT[i];
		}
		return sum;
	}

	public int sumRange(int i, int j) {
		return _queryBIT(j) - _queryBIT(i - 1);
	}

	static public class Test {
		static public void randomTest() {
			int[] nums = {1, 3, 5};
			RangeSumQueryMutable _solution = new RangeSumQueryMutable(nums);
			System.out.println(_solution.sumRange(0, 2));
			_solution.update(1, 2);
			System.out.println(_solution.sumRange(0, 2));
		}
	}
}
