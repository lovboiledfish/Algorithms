package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 1/25/16.
 */
public class KSum {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        if (A == null || A.length < k)
            return 0;

        int[][][] dp = new int[2][k + 1][target + 1];
        for (int i = 0; i < A.length; ++i) {
            dp[i % 2][0][0] = 1;
            for (int j = 1; j <= k; ++j) {
                for (int t = 1; t <= target; ++t) {
                    dp[(i + 1) % 2][j][t] = (t < A[i] ? 0 : dp[i % 2][(j - 1)][t - A[i]]) + dp[i % 2][j][t];
                }
            }
        }
        return dp[A.length % 2][k][target];
    }

    static public class Test {
        static private KSum _solution = new KSum();

        static public void test(int A[], int k, int target, int ans) {
            Assert.check(_solution.kSum(A, k, target) == ans);
        }

        static public void randomTest() {
            int A[] = {1,4,6,8,10,13,15,17,18,21,23,26,27,28,29,30,32,35,36};
            test(A, 9, 133, 231);
        }
    }
}
