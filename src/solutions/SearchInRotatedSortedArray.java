package solutions;

/**
 * Created by feiyi.zhan on 2/24/16.
 */
public class SearchInRotatedSortedArray {
	/**
	 *@param A : an integer rotated sorted array
	 *@param target :  an integer to be searched
	 *return : an integer
	 */
	public int search(int[] A, int target) {
		int lo = 0, hi = A.length - 1;
		while (lo < hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (A[mid] < A[hi]) {
				if (target > A[mid] && target <= A[hi]) {
					lo = mid + 1;
				} else {
					hi = mid;
				}
			} else {
				if (target >= A[lo] && target <= A[mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
		}
		return A.length > lo && A[lo] == target ? lo : -1;
	}
}
