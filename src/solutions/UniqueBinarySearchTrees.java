package solutions;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class UniqueBinarySearchTrees {
	/**
	 * @paramn n: An integer
	 * @return: An integer
	 */
	public int numTrees(int n) {
		if (n <= 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < dp.length; ++i) {
			for (int j = 0; j < (i >> 1); ++j) {
				dp[i] += dp[j] * dp[i - 1 - j] * 2;
			}
			if ((i & 1) == 1) {
				dp[i] += dp[i >> 1] * dp[i >> 1];
			}
		}
		return dp[n];
	}
}
