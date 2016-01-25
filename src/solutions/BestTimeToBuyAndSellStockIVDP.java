package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 1/24/16.
 */
public class BestTimeToBuyAndSellStockIVDP {
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length >> 1) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; ++i)
                maxProfit += Math.max(0, prices[i] - prices[i - 1]);
            return maxProfit;
        }

        int[] sells = new int[k+1];
        int[] holds = new int[k+1];
        Arrays.fill(holds, Integer.MIN_VALUE);
        for (int p : prices) {
            for (int i = k; i > 0; --i) {
                sells[i] = Math.max(sells[i], holds[i] + p);
                holds[i] = Math.max(holds[i], sells[i - 1] - p);
            }
        }
        return sells[k];
    }

    static public class Test {
        static private BestTimeToBuyAndSellStockIVDP _solution = new BestTimeToBuyAndSellStockIVDP();

        static public void test(int k, int[] prices, int ans) {
            Assert.check(ans == _solution.maxProfit(k, prices));
        }

        static public void randomTest() {
            test(100, new int[]{5,2,3,0,3,5,6,8,1,5}, 13);
        }
    }
}
