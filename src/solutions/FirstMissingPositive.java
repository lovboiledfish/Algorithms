package solutions;

/**
 * Created by feiyi.zhan on 2/21/16.
 */
public class FirstMissingPositive {
	/**
	 * @param A: an array of integers
	 * @return: an integer
	 */
	public int firstMissingPositive(int[] A) {
		if (A == null || A.length == 0) {
			return 1;
		}
		int i = 0;
		for (; i < A.length; ++i) {
			int swp = A[i] - 1;
			while (swp != i && swp >= 0 && swp < A.length && A[swp] - 1 != swp) {
				int tmp = A[i];
				A[i] = A[swp];
				A[swp] = tmp;
				swp = A[i] - 1;
			}
		}
		for (i = 0; i < A.length && A[i] == i + 1; ++i);
		return i + 1;
	}
}
