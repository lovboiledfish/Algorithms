package solutions;

/**
 * Created by feiyi.zhan on 2/27/16.
 */
public class SearchInsertPosition {
	/**
	 * param A : an integer sorted array
	 * param target :  an integer to be inserted
	 * return : an integer
	 */
	public int searchInsert(int[] A, int target) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int lo = 0, hi = A.length - 1, ans = -1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (target > A[mid]) {
				ans = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		++ans;
		return ans;
	}
}
