package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class MinimumWindowSubstring {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        if (source == null || target == null)
            return "";

        int[] charT = new int[256];
        int cntT = 0;
        for (char c : target.toCharArray()) {
            ++charT[c];
            ++cntT;
        }
        int[] charS = new int[256];
        int cntS = 0;
        int slow = 0;
        int start = 0, length = Integer.MAX_VALUE;
        for (int fast = 0; fast < source.length(); ++fast) {
            char f = source.charAt(fast);
            if (charS[f] < charT[f]) {
                ++cntS;
            }
            ++charS[f];
            while (cntS == cntT) {
                if (fast - slow + 1 < length) {
                    start = slow;
                    length = fast - slow + 1;
                }
                if (slow < fast) {
                    char s = source.charAt(slow);
                    --charS[s];
                    if (charS[s] < charT[s]) {
                        --cntS;
                    }
                    ++slow;
                } else
                    break;
            }
        }
        return length > source.length() ? "" : source.substring(start, start + length);
    }
}
