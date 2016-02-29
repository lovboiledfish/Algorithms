package solutions;

import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class UniquePaths {
	/**
	 * @param n, m: positive integer (1 <= n ,m <= 100)
	 * @return an integer
	 */
	public int uniquePaths(int m, int n) {
		int[][] paths = new int[2][n + 1];
		Arrays.fill(paths[0], 1);
		for (int i = 1; i < m; ++i) {
			Arrays.fill(paths[i & 1], 0);
			for (int j = 1; j <= n; ++j) {
				paths[i & 1][j] = paths[i & 1][j - 1] + paths[(i - 1) & 1][j];
			}
		}
		return paths[(m - 1) & 1][n];
	}
}
