package solutions;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class FastPower {
	/**
	 * @param a, b, n: 32bit integers
	 * @return: An integer
	 */
	public int fastPower(int a, int b, int n) {
		if (n == 1) {
			return a % b;
		}
		if (n == 0) {
			return 1 % b;
		}

		long mod = fastPower(a, b, n >> 1);
		mod = (mod * mod) % b;
		if ((n & 1) == 1) {
			mod = (mod * (a % b)) % b;
		}
		return (int) mod;
	}
}
