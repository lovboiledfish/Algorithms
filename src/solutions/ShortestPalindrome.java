package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/9/15.
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";

        char[] T = new char[(s.length() << 1) + 1];
        int[] P = new int[T.length];
        for (int i = 0; i < s.length(); ++i) {
            T[i << 1] = '#';
            T[(i << 1) + 1] = s.charAt(i);
        }
        T[T.length - 1] = '#';

        int L = 0, R = 2, length = 1, idx = 1;
        P[1] = 1;
        for (int i = 2; i < T.length; ++i) {
            if (i > R) {
                L = i;
                R = i;
            }
            int iMirror = L + R - i;
            if (iMirror != i && P[iMirror] < R - i)
                P[i] = P[iMirror];
            else {
                P[i] = (iMirror == i)? 0 : R - i;
                while (++P[i] <= i && P[i] + i < T.length && T[P[i]+i] == T[i -P[i]]);
                --P[i];
                if (i + P[i] > R) {
                    R = i + P[i];
                    L = i - P[i];
                }
                if (P[i] > length && P[i] == i) {
                    length = P[i];
                    idx = i;
                }
            }
        }
        String tail = s.substring((idx+length)/2);
        String head = new StringBuilder(tail).reverse().toString();
        return head+s;
    }

    static public class Test {
        static private ShortestPalindrome _solution = new ShortestPalindrome();

        static public void test(String s, String palin) {
            Assert.check(palin.equals(_solution.shortestPalindrome(s)));
        }

        static public void randomTest() {
            test("abaaba", "abaaba");
            test("a", "a");
            test("bb", "bb");
            test("abb", "bbabb");
        }
    }
}
