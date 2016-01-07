package solutions;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 1/6/16.
 */
public class BestTimeToBuyAndSellStockIV {
	public int maxProfit(int k, int[] prices) {
		if (prices == null || k < 1 || prices.length < 2) return 0;

		Stack<int[]> trades = new Stack<>();
		PriorityQueue<Integer> profits = new PriorityQueue<>(Collections.reverseOrder());
		int prev = Integer.MAX_VALUE, cur = prices[0], next = prices[1];
		int vally = -1, peak;
		for (int i = 0; i < prices.length; ++i) {
			if (prev >= cur && cur < next) {
				vally = i;
			} else if (prev < cur && cur >= next) {
				peak = i;
				int[] curTrade = {vally, peak};
				while (!trades.isEmpty()) {
					int[] lastTrade = trades.peek();
					if (prices[lastTrade[0]] > prices[curTrade[0]]) {
						profits.offer(prices[lastTrade[1]] - prices[lastTrade[0]]);
						trades.pop();
					} else break;
				}
				while (!trades.isEmpty()) {
					int[] lastTrade = trades.peek();
					if (prices[lastTrade[0]] <= prices[curTrade[0]] && prices[lastTrade[1]] <= prices[curTrade[1]]) {
						trades.pop();

						int deltaProfit = prices[lastTrade[1]] - prices[curTrade[0]];
						profits.offer(deltaProfit);

						curTrade[0] = lastTrade[0];
					} else break;
				}
				trades.push(curTrade);
			}

			prev = cur;
			cur = next;
			next = (i + 2 < prices.length) ? prices[i + 2] : Integer.MIN_VALUE;
		}
		for (int[] trade : trades) profits.offer(prices[trade[1]] - prices[trade[0]]);
		int maxProfit = 0;
		for (; k > 0 && !profits.isEmpty(); --k) maxProfit += profits.poll();
		return maxProfit;
	}

	static public class Test {
		static private BestTimeToBuyAndSellStockIV _solution = new BestTimeToBuyAndSellStockIV();

		static public void test(int k, int[] prices, int ans) {
			Assert.check(ans == _solution.maxProfit(k, prices));
		}

		static public void randomTest() {
			test(2, new int[]{5,2,3,0,3,5,6,8,1,5}, 12);
		}
	}
}
