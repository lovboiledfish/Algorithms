package solutions;import java.util.HashMap;
import java.util.Map;

/**
 * Created by feiyi.zhan on 2/19/16.
 */
public class PatternBuilder {
	static public boolean construct(String text, String pattern) {
		if (pattern == null || pattern.isEmpty()) {
			return true;
		}
		if (text == null || text.length() < pattern.length()) {
			return false;
		}

		Map<Character, Integer> charCnts = new HashMap<Character, Integer>();
		for (char c : pattern.toCharArray()) {
			int prevCnt = 0;
			if (charCnts.containsKey(c)) {
				prevCnt = charCnts.get(c);
			}
			charCnts.put(c, prevCnt + 1);
		}

		for (char c : text.toCharArray()) {
			if (charCnts.containsKey(c)) {
				int prevCnt = charCnts.get(c);
				if (prevCnt == 1) {
					charCnts.remove(c);
				} else {
					charCnts.put(c, prevCnt - 1);
				}
			}
			if (charCnts.isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
