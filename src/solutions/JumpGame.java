package solutions;

/**
 * Created by feiyi.zhan on 3/1/16.
 */
public class JumpGame {
	/**
	 * @param A: A list of integers
	 * @return: The boolean answer
	 */
	public boolean canJump(int[] A) {
		if (A == null || A.length == 0) {
			return true;
		}
		int reach = 0;
		for (int i = 0; i <= Math.min(reach, A.length - 1); ++i) {
			reach = Math.max(reach, i + A[i]);
		}
		return reach >= A.length - 1;
	}
}
