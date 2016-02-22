package solutions;

/**
 * Created by feiyi.zhan on 2/21/16.
 */
public class LongestCommonPrefix {
	/**
	 * @param strs: A list of strings
	 * @return: The longest common prefix
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0 || strs[0].length() == 0) {
			return "";
		}
		String prefix = strs[0];
		for (int i = 1; i < strs.length; ++i) {
			int ptr = 0;
			while (ptr < Math.min(prefix.length(), strs[i].length()) &&
					prefix.charAt(ptr) == strs[i].charAt(ptr)) {
				++ptr;
			}
			if (ptr < prefix.length()) {
				prefix = prefix.substring(0, ptr);
			}
		}
		return prefix;
	}
}
