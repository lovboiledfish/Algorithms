package solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONArray;

/**
 * Created by feiyi.zhan on 1/27/16.
 */
public class ContiuousSubarraySumII {
	/**
	 * @param A an integer array
	 * @return  A list of integers includes the index of the first number and the index of the last number
	 */
	public ArrayList<Integer> continuousSubarraySumII(int[] A) {
		ArrayList<Integer> subarray = new ArrayList<Integer>() {{
			add(-1);
			add(-1);
		}};

		if (A != null && A.length > 0) {
			long maxSum = Integer.MIN_VALUE;
			long[] sums = new long[A.length + 1];
			int minIdx = 0;
			for (int i = 0; i < A.length; ++i) {
				sums[i + 1] = sums[i] + A[i];
				long cur = sums[i + 1] - sums[minIdx];
				if (cur > maxSum) {
					subarray.set(0, minIdx);
					subarray.set(1, i);
					maxSum = cur;
				}
				if (sums[i + 1] < sums[minIdx]) {
					minIdx = i + 1;
				}
			}
			int maxIdx = 1;
			for (int i = 0; i < A.length; ++i) {
				long cur = sums[A.length] - (sums[i + 1] - sums[maxIdx]);
				if (cur > maxSum) {
					subarray.set(0, i + 1);
					subarray.set(1, maxIdx - 1);
					maxSum = cur;
				}
				if (sums[i + 1] > sums[maxIdx]) {
					maxIdx = i + 1;
				}
			}
		}
		return subarray;
	}

	static public class Test {
		static private ContiuousSubarraySumII _solution = new ContiuousSubarraySumII();

		static public void test(int[] A) {
			_solution.continuousSubarraySumII(A).forEach(System.out::println);
		}

		static public void randomTest() {
			test(new int[]{-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22});

			test(new int[]{3, 1, -100, -3, 4});

			try {
				String input = Files.lines(Paths.get("./src/solutions/utils/TestSetForContinuousSubarraySumII.json"))
						.parallel() // for parallel processing
						.collect(Collectors.joining());
				JSONArray arr = new JSONArray(input);
				int[] A = new int[arr.length()];
				for (int i = 0; i < arr.length(); ++i) {
					A[i] = arr.getInt(i);
				}
				test(A);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
