package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 3/13/16.
 */
public class PowerOfFour {
	public boolean isPowerOfFour(int n) {
		return n > 0 && (n & (n - 1)) == 0 && (n & 0x5555555555555555L) > 0;
	}

	static public class Test {
		static private PowerOfFour _solution = new PowerOfFour();

		static public void test(int n, boolean ans) {
			Assert.check(ans == _solution.isPowerOfFour(n));
		}

		static public void randomTest() {
			test(1, true);
			test(-1, false);
			test(4, true);
			test(1024, true);
			test(1023,false);
		}
	}
}
