package solutions;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class MergeSortedArray {
	/**
	 * @param A: sorted integer array A which has m elements,
	 *           but size of A is m+n
	 * @param B: sorted integer array B which has n elements
	 * @return: void
	 */
	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		int wptr = m + n - 1;
		int aPtr = m - 1;
		int bPtr = n - 1;
		while (wptr >= 0) {
			if (bPtr < 0 || (aPtr >= 0 && A[aPtr] > B[bPtr])) {
				A[wptr] = A[aPtr--];
			} else {
				A[wptr] = B[bPtr--];
			}
			--wptr;
		}
	}
}
