package solutions;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class FlipBits {
	/**
	 *@param a, b: Two integer
	 *return: An integer
	 */
	public static int bitSwapRequired(int a, int b) {
		int cnt = 0;
		while (a != 0 || b != 0) {
			if ((a & 1) != (b & 1)) {
				++cnt;
			}
			a >>>= 1;
			b >>>= 1;
		}
		return cnt;
	}
}
