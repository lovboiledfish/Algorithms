package solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PPlovboiledfish on 12/28/15.
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> ptnToStr = new HashMap<>();
        Map<String, Character> strToPtn = new HashMap<>();
        return _DFS(pattern, str, ptnToStr, strToPtn);
    }

    private boolean _DFS(String pattern, String str, Map<Character, String> ptnToStr, Map<String, Character> strToPtn) {
        if (pattern.length() == 0)
            return str.length() == 0;

        char p = pattern.charAt(0);
        if (ptnToStr.containsKey(p)) {
            String s = ptnToStr.get(p);
            if (str.length() >= s.length() && s.equals(str.substring(0, s.length())))
                return _DFS(pattern.substring(1), str.substring(s.length()), ptnToStr, strToPtn);
        } else {
            for (int i = 1; i <= str.length() - pattern.length() + 1; ++i) {
                String s = str.substring(0, i);
                if (!strToPtn.containsKey(s)) {
                    ptnToStr.put(p, s);
                    strToPtn.put(s, p);
                    if (_DFS(pattern.substring(1), str.substring(s.length()), ptnToStr, strToPtn))
                        return true;
                    strToPtn.remove(s);
                    ptnToStr.remove(p);
                }
            }
        }
        return false;
    }
}
