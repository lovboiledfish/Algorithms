package solutions.utils;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class UpdateBits {
	/**
	 *@param n, m: Two integer
	 *@param i, j: Two bit positions
	 *return: An integer
	 */
	public int updateBits(int n, int m, int i, int j) {
		final int l = j - i + 1;
		int mask = l < 32 ? ~(((1 << l) - 1) << i) : 0;
		n &= mask;
		m <<= i;
		return (n | m);
	}
}
