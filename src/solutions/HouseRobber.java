package solutions;

/**
 * Created by PPlovboiledfish on 1/23/16.
 */
public class HouseRobber {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        long[] dp = new long[2];
        int cur = 0;
        long max = 0;
        for (int aA : A) {
            dp[cur] = max + aA;
            max = Math.max(max, dp[1 - cur]);
            cur = 1 - cur;
        }
        max = Math.max(max, dp[1 - cur]);
        return max;
    }
}
