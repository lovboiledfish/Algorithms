package solutions;

import java.util.Arrays;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 1/5/16.
 */
public class DungeonGame {
	public int calculateMinimumHP(int[][] dungeon) {
		if (dungeon == null || dungeon.length == 0)
			return 0;

		int[][] dp = new int[2][dungeon[0].length];
		int cur = 0;
		Arrays.fill(dp[1 - cur], Integer.MAX_VALUE);
		dp[1 - cur][dp[0].length - 1] = 1;
		for (int i = dungeon.length - 1; i >= 0; --i) {
			for (int j = dungeon[0].length - 1; j >= 0; --j) {
				int right = (j < dungeon[0].length - 1) ? dp[cur][j + 1] : Integer.MAX_VALUE;
				int lower = dp[1 - cur][j];
				dp[cur][j] = Math.min(right, lower) - dungeon[i][j];
				if (dp[cur][j] < 1) dp[cur][j] = 1;
			}
			cur = 1 - cur;
		}
		return dp[1 - cur][0];
	}

	static public class Test {
		static private DungeonGame _solution = new DungeonGame();

		static public void test(int[][] dungeon, int ans) {
			Assert.check(ans == _solution.calculateMinimumHP(dungeon));
		}

		static public void randomTest() {
			test(new int[][]{{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}}, 3);
		}
	}
}
