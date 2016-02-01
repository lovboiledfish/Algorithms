package solutions;

import com.sun.tools.javac.util.Assert;

/**
 *
 * Algorithm to compute count of routes from start point to end point on a 2D m*n chess board.
 * The routes must be of K steps.
 *
 * Created by feiyi.zhan on 2/1/16.
 */
public class CountRoutesOfKSteps {
	static private int[][] _moves = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	public int countRoutes(int m, int n, int[] start, int[] end, int k) {
		int[][][] dp = new int[m][n][k + 1];
		dp[start[0]][start[1]][0] = 1;
		for (int kk = 1; kk <= k; ++kk) {
			for (int i = Math.max(0, start[0] - kk); i <= Math.min(m - 1, start[0] + kk); ++i) {
				for (int j = Math.max(0, start[1] - kk); j <= Math.min(n - 1, start[1] + kk); ++j) {
					if (i > 0)
						dp[i][j][kk] += dp[i - 1][j][kk - 1];
					if (j > 0)
						dp[i][j][kk] += dp[i][j - 1][kk - 1];
					if (i < m - 1)
						dp[i][j][kk] += dp[i + 1][j][kk - 1];
					if (j < n - 1)
						dp[i][j][kk] += dp[i][j + 1][kk - 1];
				}
			}
		}
		return dp[end[0]][end[1]][k];
	}

	static public class Test {
		static private CountRoutesOfKSteps _solution = new CountRoutesOfKSteps();

		static public void test(int m, int n, int[] start, int[] end, int k, int ans) {
			Assert.check(_solution.countRoutes(m, n, start, end, k) == ans);
		}

		static public void randomTest() {
			// no route
			test(5, 5, new int[]{1, 0}, new int[]{0, 2}, 2, 0);

			// one route
			test(5, 8, new int[]{2, 4}, new int[]{2, 2}, 2, 1);

			// two routes
			test(5, 8, new int[]{2, 4}, new int[]{1, 3}, 2, 2);
		}
	}
}
