package solutions;

import java.util.Arrays;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 2/13/16.
 */
public class WildcardMatching {
	public boolean isMatchRecursive(String s, String p) {
		if (s == null || p == null) {
			return s == null && p == null;
		}
		int sptr = 0, pptr = 0;
		while (pptr < p.length()) {
			if (p.charAt(pptr) == '*') {
				++pptr;
				if (pptr == p.length()) {
					return true;
				}
				for (; sptr < s.length(); ++sptr) {
					if (isMatchRecursive(s.substring(sptr), p.substring(pptr))) {
						return true;
					}
				}
			} else {
				if (sptr < s.length() && (p.charAt(pptr) == '?' || p.charAt(pptr) == s.charAt(sptr))) {
					++sptr;
					++pptr;
				} else {
					return false;
				}
			}
		}
		return sptr == s.length();
	}

	public boolean isMatchDP(String s, String p) {
		if (s == null || p == null) {
			return s == null && p == null;
		}
		boolean[][] match = new boolean[2][s.length() + 1];
		match[0][0] = true;
		int cur = 1;
		for (int pptr = 1; pptr <= p.length(); ++pptr, cur = 1 - cur) {
			Arrays.fill(match[cur], false);
			match[cur][0] = match[1 - cur][0] & p.charAt(pptr - 1) == '*';
			for (int sptr = 1; sptr <= s.length(); ++sptr) {
				if (p.charAt(pptr - 1) == '*') {
					for (int k = 0; k <= sptr; ++k) {
						if (match[1 - cur][k]) {
							match[cur][sptr] = true;
							break;
						}
					}
				} else {
					match[cur][sptr] = match[1 - cur][sptr - 1] & (p.charAt(pptr - 1) == '?' || p.charAt(pptr - 1) == s.charAt(sptr - 1));
				}
			}
		}
		return match[1 - cur][s.length()];
	}

	public boolean isMatch(String str, String pattern) {
		int sPtr = 0, pPtr = 0, star = -1, matched = 0;
		while (sPtr < str.length()) {
			// Finite state machine
			char p = pPtr == pattern.length() ? '\0' : pattern.charAt(pPtr);
			if (p == '?' || str.charAt(sPtr) == p) {
				++sPtr;
				++pPtr;
			} else if (p == '*') {
				star = pPtr;
				matched = sPtr;
				++pPtr;
			} else if (star >= 0) {
				pPtr = star + 1;
				++matched;
				sPtr = matched;
			} else {
				return false;
			}
		}
		while (pPtr < pattern.length() && pattern.charAt(pPtr) == '*') {
			++pPtr;
		}
		return pPtr == pattern.length();
	}

	static public class Test {
		static private WildcardMatching _solution = new WildcardMatching();

		static public void test(String s, String p, boolean ans) {
			Assert.check(_solution.isMatch(s, p) == ans);
		}

		static public void randomTest() {
			test("aaa", "a?a", true);
			test("aaa", "a?", false);
			test("", "?", false);
			test("aa", "*?", true);
			test("abcde", "?*f", false);
			test("aab", "c*a*b", false);
			test("", "*", true);
			test("a", "a*", true);
			test("b", "*?*?*", false);
			test("abaaaa", "a*a*a", true);
			test("b", "?*?", false);
		}
	}
}
