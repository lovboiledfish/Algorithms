package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || s.isEmpty())
            return t != null && t.length() == 1;
        if (t == null || t.isEmpty())
            return s.length() == 1;

        if (s.length() > t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }
        if (t.length() - s.length() > 1)
            return false;

        boolean diff = false;
        for (int i = 0, j = 0; i < s.length(); ++i, ++j) {
            if (s.charAt(i) != t.charAt(j)) {
                if (diff)
                    return false;
                diff = true;
                if (s.length() < t.length())
                    --i;
            }
        }
        return diff || t.length() > s.length();
    }

    static public class Test {
        static private OneEditDistance _solution = new OneEditDistance();

        static public void test(String s, String t, boolean ans) {
            Assert.check(ans == _solution.isOneEditDistance(s, t));
        }

        static public void randomTest() {
            test("coca", "coda", true);
        }

        static public void failure1() {
            test("cba", "ba", true);
        }

        static public void failure2() {
            test("a", "ac", true);
        }

        static public void failure3() {
            test("teacher", "tepache", false);
        }
    }
}
