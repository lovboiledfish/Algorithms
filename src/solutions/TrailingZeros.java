package solutions;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class TrailingZeros {
	/**
	 * param n: As desciption
	 * return: An integer, denote the number of trailing zeros in n!
	 */
	public long trailingZeros(long n) {
		long cnt = 0;
		while (n > 0) {
			n /= 5;
			cnt += n;
		}
		return cnt;
	}
}
