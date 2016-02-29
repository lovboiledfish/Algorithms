package solutions;

/**
 * Created by feiyi.zhan on 2/28/16.
 */
public class CheckPowerOf2 {
	/**
	 * @param n: An integer
	 * @return: True or false
	 */
	public boolean checkPowerOf2(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}
}
