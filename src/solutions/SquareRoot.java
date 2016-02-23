package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class SquareRoot {
	/**
	 * @param x: An integer
	 * @return: The sqrt of x
	 */
	public int sqrt(int x) {
		int ans = -1;
		if (x >= 0) {
			float re = 1;
			while (true) {
				float next = re / 2 + x / (2 * re);
				if (Math.abs(next - re) < 0.5) {
					ans = (int) re;
					break;
				}
				re = next;
			}
		}
		return ans;
	}

	static public class Test {
		static private SquareRoot _solution = new SquareRoot();

		static public void test(int x, int ans) {
			Assert.check(_solution.sqrt(x) == ans);
		}

		static public void randomTest() {
			test(0, 0);
		}
	}
}
