package solutions;

/**
 * Created by PPlovboiledfish on 12/27/15.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;

        int[][] DP = new int[2][costs[0].length];
        System.arraycopy(costs[0], 0, DP[0], 0, costs[0].length);
        for (int i = 1; i < costs.length; ++i) {
            for (int j = 0; j < costs[0].length; ++j) {
                DP[i&1][j] = Integer.MAX_VALUE;
                for (int k = 0; k < costs[0].length; ++k) {
                    if (k != j && DP[1-(i&1)][k] + costs[i][j] < DP[i&1][j])
                        DP[i&1][j] = DP[1-(i&1)][k] + costs[i][j];
                }
            }
        }
        int cur = 1 - (costs.length & 1);
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; ++i)
            if (minCost > DP[cur][i])
                minCost = DP[cur][i];
        return minCost;
    }
}
