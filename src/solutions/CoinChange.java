package solutions;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 2/12/16.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }

        int[] minCnt = new int[amount + 1];
        Arrays.fill(minCnt, Integer.MAX_VALUE);
        for (int coin : coins) {
            if (coin <= amount) {
                minCnt[coin] = 1;
            }
        }
        for (int i = 1; i <= amount; ++i) {
            if (minCnt[i] == Integer.MAX_VALUE) {
                for (int coin : coins) {
                    if (i >= coin && minCnt[i - coin] != Integer.MAX_VALUE) {
                        minCnt[i] = Math.min(minCnt[i], minCnt[i - coin] + 1);
                    }
                }
            }
        }
        return minCnt[amount] == Integer.MAX_VALUE ? -1 : minCnt[amount];
    }
}
