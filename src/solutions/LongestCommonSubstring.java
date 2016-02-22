package solutions;

import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/21/16.
 */
public class LongestCommonSubstring {
	/**
	 * @param A, B: Two string.
	 * @return: the length of the longest common substring.
	 */
	public int longestCommonSubstring(String A, String B) {
		if (A == null || A.length() == 0 || B == null || B.length() == 0) {
			return 0;
		}

		final int m = A.length(), n = B.length();
		int[][] count = new int[2][n + 1];
		int maxLength = 0;
		for (int i = 0; i < m; ++i) {
			Arrays.fill(count[(i + 1) % 2], 0);
			for (int j = 0; j < n; ++j) {
				if (A.charAt(i) == B.charAt(j)) {
					count[(i + 1) % 2][j + 1] = count[i % 2][j] + 1;
					maxLength = Math.max(maxLength, count[(i + 1) % 2][j + 1]);
				}
			}
		}
		return maxLength;
	}
}
