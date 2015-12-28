package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/9/15.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return "";

        char[] T = new char[s.length() * 2 + 1];
        for (int i = 0; i < s.length(); ++i) {
            T[2 * i] = '#';
            T[2 * i + 1] = s.charAt(i);
        }
        T[s.length() * 2] = '#';
        int[] P = new int[T.length];

        int max = 1, maxIdx = 1, L = 0, R = 2;
        P[1] = 1;
        for (int i = 2; i < T.length; ++i) {
            if (i > R) {
                R = i;
                L = i;
            }
            int iBar = L + R - i;
            if (iBar != i && P[iBar] < R - i) {
                P[i] = P[iBar];
            } else {
                int j = (iBar != i)? R - i + 1 : 1;
                for (; i + j < T.length && i >= j; ++j)
                    if (T[i + j] != T[i - j])
                        break;
                P[i] = j - 1;
                if (i + P[i] > R) {
                    R = i + P[i];
                    L = i - P[i];
                }
                if (P[i] > max) {
                    max = P[i];
                    maxIdx = i;
                }
            }
        }
        return s.substring((maxIdx - max)/2, (maxIdx + max)/2);
    }

    static public class Test {
        static private LongestPalindromicSubstring _solution = new LongestPalindromicSubstring();

        static public void test(String s, String palin) {
            Assert.check(palin.equals(_solution.longestPalindrome(s)));
        }

        static public void randomTest() {
            test("abaaba", "abaaba");
            test("babcbabcbaccba", "abcbabcba");
            test("a", "a");
            test("bb", "bb");
            test("abb", "bb");
        }
    }
}
