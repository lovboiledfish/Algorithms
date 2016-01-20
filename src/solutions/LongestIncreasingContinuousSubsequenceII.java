package solutions;

/**
 * Created by PPlovboiledfish on 1/20/16.
 */
public class LongestIncreasingContinuousSubsequenceII {
    static private int[][] _moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * @param A an integer matrix
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A == null || A.length == 0)
            return 0;

        int[][] dp = new int[A.length][A[0].length];
        int length = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                length = Math.max(length, _dfs(A, dp, i, j));
            }
        }
        return length;
    }

    private int _dfs(int[][] A, int[][] dp, int row, int col) {
        if (dp[row][col] > 0)
            return dp[row][col];

        int length = 0;
        for (int[] move : _moves) {
            int x = row + move[0], y = col + move[1];
            if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && A[x][y] > A[row][col]) {
                length = Math.max(length, _dfs(A, dp, x, y));
            }
        }
        ++length;
        dp[row][col] = length;
        return length;
    }
}
