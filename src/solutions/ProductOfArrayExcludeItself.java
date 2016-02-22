package solutions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/22/16.
 */
public class ProductOfArrayExcludeItself {
	/**
	 * @param A: Given an integers array A
	 * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
	 */
	public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
		long[] left = new long[A.size() + 1];
		Arrays.fill(left, 1L);
		long[] right = new long[A.size() + 1];
		Arrays.fill(right, 1L);
		for (int i = 0; i < A.size(); ++i) {
			left[i + 1] = A.get(i) * left[i];
			right[A.size() - 1 - i] = A.get(A.size() - 1 - i) * right[A.size() - i];
		}
		ArrayList<Long> ret = new ArrayList<Long>();
		for (int i = 0; i < A.size(); ++i) {
			ret.add(left[i] * right[i + 1]);
		}
		return ret;
	}
}
