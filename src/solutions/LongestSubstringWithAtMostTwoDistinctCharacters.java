package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 12/8/15.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null)
            return 0;
        if (s.length() < 3)
            return s.length();

        int l = 0, r = 1, count = 1, maxL = 2;
        int[] registry = new int[256];
        registry[s.charAt(0)] = 1;
        while (r < s.length()) {
            if (registry[s.charAt(r)] == 0) {
                ++count;
                registry[s.charAt(r)] = 1;
            } else
                ++registry[s.charAt(r)];
            while (count > 2) {
                --registry[s.charAt(l)];
                if (registry[s.charAt(l)] == 0)
                    --count;
                ++l;
            }
            while (count < 2 && l > 0) {
                --l;
                if (registry[s.charAt(l)] == 0)
                    ++count;
                ++registry[s.charAt(l)];
            }
            maxL = Math.max(maxL, r - l + 1);
            ++r;
        }
        return maxL;
    }

    static public class Test {
        static private LongestSubstringWithAtMostTwoDistinctCharacters _solution = new LongestSubstringWithAtMostTwoDistinctCharacters();

        static public void test(String s, int ans) {
            Assert.check(ans == _solution.lengthOfLongestSubstringTwoDistinct(s));
        }

        static public void randomTest() {
            test("eceba", 3);
            test("aaaaaaa", 7);
            test("baaaaaab", 8);
            test("baaaaaabb", 9);
            test("abccccccbaa", 8);
        }
    }
}
