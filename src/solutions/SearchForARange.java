package solutions;

/**
 * Created by feiyi.zhan on 2/24/16.
 */
public class SearchForARange {
	/**
	 *@param A : an integer sorted array
	 *@param target :  an integer to be inserted
	 *return : a list of length 2, [index1, index2]
	 */
	public int[] searchRange(int[] A, int target) {
		int left = _searchLeft(A, 0, A.length - 1, target);
		if (left >= 0) {
			return new int[]{left, _searchRight(A, left, A.length - 1, target)};
		} else {
			return new int[]{-1, -1};
		}
	}

	private int _searchLeft(int[] A, int lo, int hi, int target) {
		int left = lo - 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (A[mid] < target) {
				left = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		++left;
		return A.length > left && A[left] == target ? left : -1;
	}

	private int _searchRight(int[] A, int lo, int hi, int target) {
		int right = hi + 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (A[mid] <= target) {
				lo = mid + 1;
			} else {
				right = mid;
				hi = mid - 1;
			}
		}
		--right;
		return A.length > right && A[right] == target ? right : -1;
	}
}
