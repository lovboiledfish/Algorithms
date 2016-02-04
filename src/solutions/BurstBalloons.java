package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 2/3/16.
 */
public class BurstBalloons {
	public int maxCoins(int[] nums) {
		int[] myNums = new int[nums.length + 2];
		System.arraycopy(nums, 0, myNums, 1, nums.length);
		myNums[0] = 1;
		myNums[myNums.length - 1] = 1;
		int[][] memoiz = new int[myNums.length][myNums.length];
		return _maxCoins(myNums, 1, nums.length, memoiz);
	}

	private int _maxCoins(int[] nums, int start, int end, int[][] memoiz) {
		if (memoiz[start][end] == 0) {
			int ans = 0;
			int factor = nums[start - 1] * nums[end + 1];
			for (int k = start; k <= end; ++k) {
				int left = _maxCoins(nums, start, k - 1, memoiz);
				int right = _maxCoins(nums, k + 1, end, memoiz);
				ans = Math.max(ans, left + right + nums[k] * factor);
			}
			memoiz[start][end] = ans;
		}
		return memoiz[start][end];
	}

	static public class Test {
		static private BurstBalloons _solution = new BurstBalloons();

		static public void test(int[] nums, int ans) {
			Assert.check(ans == _solution.maxCoins(nums));
		}

		static public void randomTest() {
			test(new int[]{3, 1, 5, 8}, 167);
			test(new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5, 5}, 3830);
		}
	}
}
