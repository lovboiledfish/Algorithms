package solutions;

/**
 * Created by PPlovboiledfish on 1/25/16.
 */
public class CoinsInALineIII {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0)
            return true;

        int[][] dp = new int[3][values.length];
        int sum = 0;
        for (int i = values.length - 1; i >= 0; --i) {
            sum += values[i];
            dp[i % 3][i] = values[i];
            if (i + 1 < values.length)
                dp[i % 3][i + 1] = Math.max(values[i], values[i + 1]);
            for (int j = i + 2; j < values.length; ++j) {
                dp[i % 3][j] = Math.max(Math.min(dp[(i + 2) % 3][j], dp[(i + 1) % 3][j - 1]) + values[i],
                        Math.min(dp[i % 3][j - 2], dp[(i + 1) % 3][j - 1]) + values[j]);
            }
        }
        return (dp[0][values.length - 1] << 1) > sum;
    }
}
