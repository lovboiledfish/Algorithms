package solutions;

/**
 * Created by feiyi.zhan on 3/2/16.
 */
public class GasStation {
	/**
	 * @param gas: an array of integers
	 * @param cost: an array of integers
	 * @return: an integer
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int stats = 0;
		int start = 0;
		final int n = gas.length;
		for (int i = 0; i < (n << 1) && start < n; ++i) {
			stats += (gas[i % n] - cost[i % n]);
			if (stats < 0) {
				stats = 0;
				start = i + 1;
			} else if (i - start == gas.length - 1) {
				return start;
			}
		}
		return -1;
	}
}
