package solutions;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class RemoveElement {
	/**
	 *@param A: A list of integers
	 *@param elem: An integer
	 *@return: The new length after remove
	 */
	public int removeElement(int[] A, int elem) {
		int wptr = 0;
		for (int i = 0; i < A.length; ++i) {
			if (A[i] != elem) {
				if (wptr != i) {
					A[wptr] = A[i];
				}
				++wptr;
			}
		}
		return wptr;
	}
}
