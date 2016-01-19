package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;

        int[] chars = new int[256];
        chars[s.charAt(0)] = 1;
        int count = 1;
        int l = 0, r = 0, maxSize = -1;
        while (l < s.length()) {
            if (count <= k) {
                maxSize = Math.max(maxSize, r - l + 1);
                ++r;
                if (r < s.length()) {
                    if (chars[s.charAt(r)] == 0) {
                        ++count;
                    }
                    ++chars[s.charAt(r)];
                } else
                    break;
            } else {
                --chars[s.charAt(l)];
                if (chars[s.charAt(l)] == 0) {
                    --count;
                }
                ++l;
            }
        }
        return maxSize;
    }
}
