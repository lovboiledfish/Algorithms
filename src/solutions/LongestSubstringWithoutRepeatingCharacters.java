package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int[] chars = new int[256];
        int count = 0, length = 0;
        int slow = 0;
        for (int fast = 0; fast < s.length(); ++fast) {
            char f = s.charAt(fast);
            if (chars[f] == 1)
                ++count;
            ++chars[f];
            while (count > 0 && slow < fast) {
                char sl = s.charAt(slow);
                if (chars[sl] == 2)
                    --count;
                --chars[sl];
                ++slow;
            }
            if (count == 0)
                length = Math.max(length, fast - slow + 1);
        }
        return length;
    }
}
