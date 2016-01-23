package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 10/31/15.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int[][] dp = new int[2][matrix[0].length + 1];
        int maxArea = 0;
        int cur = 0;
        for (char[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (aMatrix[j] == '1') {
                    dp[cur][j + 1] = Math.min(dp[cur][j], Math.min(dp[1 - cur][j], dp[1 - cur][j + 1])) + 1;
                    maxArea = Math.max(maxArea, dp[cur][j + 1]);
                } else {
                    dp[cur][j + 1] = 0;
                }
            }
            cur = 1 - cur;
        }
        return maxArea * maxArea;
    }

    static public class Test {
        static private MaximalSquare _solution = new MaximalSquare();

        static public void test(char[][] matrix, int ans) {
            int ret = _solution.maximalSquare(matrix);
            Assert.check(ret == ans);
        }

        static public void randomTest() {
            char[][] matrix = {
                    {'1', '0', '1', '0', '0'},
                    {'1', '0', '1', '1', '1'},
                    {'1', '1', '1', '1', '1'},
                    {'1', '0', '0', '1', '0'}
            };

            test(matrix, 4);
        }

        static public void failure1() {
            char[][] matrix = {
                    {'0', '0', '0'},
                    {'0', '0', '0'},
                    {'0', '0', '0'},
                    {'0', '0', '0'}
            };

            test(matrix, 0);
        }

        static public void failure2() {
            char[][] matrix = {{'1'}};

            test(matrix, 1);
        }
    }
}
