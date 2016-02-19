package solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feiyi.zhan on 2/18/16.
 */
public class AmicablePair {
	public List<int[]> findPairs(int n) {
		List<int[]> ans = new ArrayList<>();
		if (n > 0) {
			int[] divisorSum = new int[n + 1];
			for (int num = 2; num <= n; ++num) {
				divisorSum[num] = _divisorSum(num);
				if (divisorSum[num] <= n &&
						divisorSum[divisorSum[num]] == num &&
						num != divisorSum[num]) {
					ans.add(new int[]{divisorSum[num], num});
				}
			}
		}
		return ans;
	}

	private int _divisorSum(int k) {
		int pivot = (int) Math.sqrt(k);
		int divisorSum = 1;
		for (int i = 2; i <= pivot; ++i) {
			if (k % i == 0) {
				divisorSum += k / i + i;
			}
		}
		return divisorSum;
	}

	static public class Test {
		static private AmicablePair _solution = new AmicablePair();

		static public void test(int n) {
			_solution.findPairs(n).forEach(pair -> System.out.println(pair[0] + ", " + pair[1]));
		}

		static public void randomTest() {
			test(100000);
		}
	}
}
