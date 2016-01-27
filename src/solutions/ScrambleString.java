package solutions;

/**
 * Created by feiyi.zhan on 1/26/16.
 */
public class ScrambleString {
	/**
	 * @param s1 A string
	 * @param s2 Another string
	 * @return whether s2 is a scrambled string of s1
	 */
	public boolean isScramble(String s1, String s2) {
		if (s1 == null)
			return s2 == null;
		if (s1.length() != s2.length())
			return false;
		if (s1.length() == 0)
			return true;

		int size = s1.length();
		boolean[][][] dp = new boolean[size][size][size + 1];
		for (int i = size - 1; i >= 0; --i) {
			for (int j = size - 1; j >= 0; --j) {
				dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
				for (int k = 2; k + i < size + 1; ++k) {
					dp[i][j][k] = false;
					for (int a = 1; a < k; ++a) {
						if (j + a < size)
							dp[i][j][k] |= (dp[i][j][a] & dp[i + a][j + a][k - a]);
						if (j + k - a < size)
							dp[i][j][k] |= (dp[i][j + k - a][a] & dp[i + a][j][k - a]);
						if (dp[i][j][k])
							break;
					}
				}
			}
		}
		return dp[0][0][size];
	}
}
