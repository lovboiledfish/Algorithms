package solutions;

import java.util.ArrayList;

/**
 * Created by feiyi.zhan on 1/27/16.
 */
public class ContinuousSubarraySum {
	/**
	 * @param A an integer array
	 * @return  A list of integers includes the index of the first number and the index of the last number
	 */
	public ArrayList<Integer> continuousSubarraySum(int[] A) {
		ArrayList<Integer> subarray = new ArrayList<Integer>() {{
			add(-1);
			add(-1);
		}};

		if (A != null && A.length > 0) {
			int maxSum = Integer.MIN_VALUE;
			final int[] sums = new int[A.length + 1];
			int minIdx = 0;
			for (int i = 0; i < A.length; ++i) {
				sums[i + 1] = sums[i] + A[i];
				int cur = sums[i + 1] - sums[minIdx];
				if (cur >= maxSum) {
					subarray.set(0, minIdx);
					subarray.set(1, i);
					maxSum = cur;
				}
				if (sums[i + 1] <= sums[minIdx]) {
					minIdx = i + 1;
				}
			}
		}
		return subarray;
	}
}
