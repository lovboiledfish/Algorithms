package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/3/15.
 */
public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length())
            return -1;
        if (haystack.isEmpty() || needle.isEmpty())
            return 0;

        int[] pattern = _buildPattern(needle);
        int hPtr = 0, nPtr = 0;
        for (; hPtr < haystack.length(); ++hPtr) {
            if (haystack.charAt(hPtr) == needle.charAt(nPtr)) {
                if (++nPtr == needle.length())
                    return hPtr + 1 - needle.length();
            } else if (nPtr != 0) {
                nPtr = pattern[nPtr];
                --hPtr;
            }
        }

        return -1;
    }

    private int[] _buildPattern(String s) {
        int[] pattern = new int[s.length() + 1];
        int matchPtr = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(matchPtr) == s.charAt(i))
                ++matchPtr;
            else if (matchPtr != 0) {
                matchPtr = pattern[matchPtr];
                --i;
                continue;
            }
            pattern[i + 1] = matchPtr;
        }
        return pattern;
    }

    static public class Test {
        static private ImplementStrStr _solution = new ImplementStrStr();

        static public void test(String haystack, String needle, int ans) {
            Assert.check(ans == _solution.strStr(haystack, needle));
        }

        static public void randomTest() {
            test("abcabd", "abcabd", 0);
            test("abcabcabd", "abcabd", 3);
            test("", null, -1);
            test("", "", 0);
            test("", "a", -1);
            test("a", "c", -1);
            test("a", "", 0);
        }
    }
}
