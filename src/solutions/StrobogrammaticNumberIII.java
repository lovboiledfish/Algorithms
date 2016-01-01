package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.TreeMap;

/**
 * Created by PPlovboiledfish on 12/31/15.
 */
public class StrobogrammaticNumberIII {
    static private TreeMap<Integer, Integer> _strobogrammaticDigits = new TreeMap<Integer, Integer>() {{
        put(0, 0);
        put(1, 1);
        put(6, 9);
        put(8, 8);
        put(9, 6);
    }};

    public int strobogrammaticInRange(String low, String high) {
        int cnt = 0, i = 1;
        while (i <= high.length()) {
            if (i >= low.length()) {
                cnt += _strobogrammaticInRange(i);
                if (i == low.length()) cnt -= _strobogrammaticInRange(i, low, true, false);
                if (i == high.length()) cnt -= _strobogrammaticInRange(i, high, false, false);
            }
            ++i;
        }
        return cnt;
    }

    private int _strobogrammaticInRange(int digits) {
        if (digits < 1) return 0;
        int strobDgtCnt = _strobogrammaticDigits.keySet().size();
        int cnt = (digits & 1) == 1 ? strobDgtCnt - 2 : 1; // digit in the middle
        if ((digits >> 1) > 1) cnt *= Math.pow(strobDgtCnt, (digits >> 1) - 1); // digits of the body
        if (digits > 1) cnt *= (strobDgtCnt - 1); // digits of the head and tail
        return cnt;
    }

    private int _strobogrammaticInRange(int digits, String limit, boolean toLeft, boolean inclusive) {
        if (digits < 1 || limit.isEmpty()) return 0;
        int cnt = 0, firstDigitCnt = 0, midDigitsCnt = (digits > 2)? _strobogrammaticInRange(digits) / 4 : 1;
        int msd = limit.charAt(0) - '0', lsd = limit.charAt(limit.length() - 1) - '0';
        Integer key = toLeft? _strobogrammaticDigits.floorKey(msd) : _strobogrammaticDigits.ceilingKey(msd);
        if (key != null && msd == key) {
            // handle irregular cases like get the count of strobos in range [600, 670]
            boolean isInclusive = false;
            int statusCode = (toLeft? 2 : 0) | (inclusive? 1 : 0);
            switch (statusCode) {
                case 0: isInclusive = lsd < _strobogrammaticDigits.get(key); break;
                case 1: isInclusive = lsd <= _strobogrammaticDigits.get(key); break;
                case 2: isInclusive = lsd > _strobogrammaticDigits.get(key); break;
                case 3: isInclusive = lsd >= _strobogrammaticDigits.get(key); break;
            }
            if (digits > 2) {
                String midPart = limit.substring(1, limit.length() - 1);
                cnt += _strobogrammaticInRange(digits - 2, midPart, toLeft, isInclusive);
            } else if (isInclusive)
                cnt += 1;
            key = toLeft? _strobogrammaticDigits.lowerKey(key) : _strobogrammaticDigits.higherKey(key);
        }
        while (key != null && ((digits > 1 && key > 0) || (digits == 1))) {
            if (digits != 1 || _strobogrammaticDigits.get(key).equals(key)) ++firstDigitCnt;
            key = toLeft? _strobogrammaticDigits.lowerKey(key) : _strobogrammaticDigits.higherKey(key);
        }
        cnt += firstDigitCnt * midDigitsCnt;
        return cnt;
    }

    static public class Test {
        static private StrobogrammaticNumberIII _solution = new StrobogrammaticNumberIII();

        static public void test(String low, String high, int ans) {
            Assert.check(ans == _solution.strobogrammaticInRange(low, high));
        }

        static public void randomTest() {
            test("1000", "10000", 20);
        }
    }
}
