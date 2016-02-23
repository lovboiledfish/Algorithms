package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class WoodCut {
	/**
	 * @param L: Given n pieces of wood with length L[i]
	 * @param k: An integer
	 *           return: The maximum length of the small pieces.
	 */
	public int woodCut(int[] L, int k) {
		int maxL = 0;
		for (int l : L) {
			maxL = Math.max(maxL, l);
		}
		int lo = 1, hi = maxL, ans = 0;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			int cuts = 0;
			for (int l : L) {
				cuts += l / mid;
			}
			if (cuts >= k) {
				lo = mid + 1;
				ans = mid;
			} else {
				hi = mid - 1;
			}
		}
		return ans;
	}

	static public class Test {
		static private WoodCut _solution = new WoodCut();

		static public void test(int[] L, int k, int ans) {
			Assert.check(_solution.woodCut(L, k) == ans);
		}

		static public void randomTest() {
			int[] L = {232, 124, 456};
			int k = 7;
			test(L, k, 114);
		}
	}
}
