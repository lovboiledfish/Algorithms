package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 1/25/16.
 */
public class StoneGame {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length < 2)
            return 0;

        int[] sum = new int[A.length + 1];
        for (int i = 0; i < A.length; ++i)
            sum[i + 1] = sum[i] + A[i];

        int[][] dp = new int[A.length][A.length];
        for (int i = A.length - 1; i >= 0; --i) {
            dp[i][i] = A[i];
            for (int j = i + 1; j < A.length; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    int delta = sum[j + 1] - sum[i];
                    if (k == i)
                        delta -= A[i];
                    if (k + 1 == j)
                        delta -= A[j];
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + delta);
                }
            }
        }
        return dp[0][A.length - 1];
    }

    static public class Test {
        static private StoneGame _solution = new StoneGame();

        static public void test(int[] A, int ans) {
            Assert.check(_solution.stoneGame(A) == ans);
        }

        static public void randomTest() {
            int[] A = {3, 4, 3};
            test(A, 17);
        }
    }
}
