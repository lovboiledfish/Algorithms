package solutions;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 1/24/16.
 */
public class BestTimeToBuyAndSellStockIVDP {
    public int maxProfit(int k, int[] prices) {
        if(k>=prices.length/2){
            int maxProfit = 0;
            for(int i=1; i<prices.length; i++)
                if(prices[i]>prices[i-1]) maxProfit += prices[i]-prices[i-1];
            return maxProfit;
        }

        int[] sells = new int[k+1];
        int[] holds = new int[k+1];
        Arrays.fill(holds, Integer.MIN_VALUE);
        for (int p : prices) {
            for (int i = 1; i <= k; ++i) {
                sells[i] = Math.max(sells[i], holds[i] + p);
                holds[i] = Math.max(holds[i], sells[i - 1] - p);
            }
        }
        return sells[k];
    }
}
