package solutions;

/**
 * Created by feiyi.zhan on 1/26/16.
 */
public class Backpack {
	/**
	 * @param m: An integer m denotes the size of a backpack
	 * @param A: Given n items with size A[i]
	 * @return: The maximum size
	 */
	public int backPack(int m, int[] A) {
		if (A == null || A.length == 0 || m == 0)
			return 0;

		int[][] dp = new int[2][m + 1];
		int cur = 0;
		for (int aA : A) {
			for (int j = 1; j <= m; ++j) {
				dp[cur][j] = Math.max((j >= aA ? dp[1 - cur][j - aA] + aA : 0), dp[1 - cur][j]);
			}
			cur = 1 - cur;
		}
		return dp[1 - cur][m];
	}
}
