package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        int[] letterCount = new int[256];
        int oddCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            ++letterCount[s.charAt(i)];
            if ((letterCount[s.charAt(i)] & 1) == 1)
                ++oddCount;
            else
                --oddCount;
        }
        return oddCount < 2;
    }

    static public class Test {
        static private PalindromePermutation _solution = new PalindromePermutation();

        static public void test(String s, boolean ans) {
            Assert.check(ans == _solution.canPermutePalindrome(s));
        }

        static public void randomTest() {
            test("code", false);
            test("aab", true);
            test("carerac", true);
        }
    }
}
