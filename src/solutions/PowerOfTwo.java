package solutions;

/**
 * Created by feiyi.zhan on 2/18/16.
 */
public class PowerOfTwo {
	public boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		while (n > 0) {
			if ((n & 1) == 1 && n != 1) {
				return false;
			}
			n >>= 1;
		}
		return true;
	}
}
