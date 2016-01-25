package solutions;

/**
 * Created by PPlovboiledfish on 1/24/16.
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int hold1 = Integer.MIN_VALUE, sell1 = 0, hold2 = Integer.MIN_VALUE, sell2 = 0;
        for (int p : prices) {
            sell2 = Math.max(sell2, hold2 + p);
            hold2 = Math.max(hold2, sell1 - p);
            sell1 = Math.max(sell1, hold1 + p);
            hold1 = Math.max(hold1, -p);
        }
        return sell2;
    }
}
