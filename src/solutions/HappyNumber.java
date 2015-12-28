package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/2/15.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        byte[] trace = new byte[1000];
        return _isHappy(n, trace);
    }

    public boolean _isHappy(int n, byte[] trace) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        if (sum == 1)
            return true;

        if (trace[sum] == 1)
            return false;
        trace[sum] = 1;
        return _isHappy(sum, trace);
    }

    static public class Test {
        static private HappyNumber _solution = new HappyNumber();

        static public void test(int n, boolean ans) {
            Assert.check(ans == _solution.isHappy(n));
        }

        static public void randomTest() {
            test(78, false);
        }
    }
}
