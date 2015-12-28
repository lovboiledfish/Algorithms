package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 12/27/15.
 */
public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> chars = new HashMap<>();
        int pivotCnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            chars.put(s.charAt(i), chars.containsKey(s.charAt(i)) ? (chars.get(s.charAt(i)) + 1) : 1);
            if ((chars.get(s.charAt(i)) & 1) == 1)
                ++pivotCnt;
            else
                --pivotCnt;
        }
        List<String> palins = new ArrayList<>();
        if (pivotCnt < 2) {
            char[] permutation = new char[s.length()];
            if (pivotCnt == 1) {
                char pivot = 0;
                for (char c : chars.keySet())
                    if ((chars.get(c) & 1) == 1) {
                        permutation[s.length() >> 1] = c;
                        pivot = c;
                        break;
                    }
                chars.put(pivot, chars.get(pivot) - 1);
            }
            _DFS(permutation, 0, chars, palins);
        }
        return palins;
    }

    private void _DFS(char[] permutiation, int wptr, Map<Character, Integer> chars, List<String> palins) {
        if (wptr == (permutiation.length >> 1)) {
            palins.add(new String(permutiation));
        } else {
            for (char c : chars.keySet()) {
                if (chars.get(c) > 0) {
                    permutiation[wptr] = c;
                    permutiation[permutiation.length - wptr - 1] = c;
                    chars.put(c, chars.get(c) - 2);
                    _DFS(permutiation, wptr + 1, chars, palins);
                    chars.put(c, chars.get(c) + 2);
                }
            }
        }
    }
}
