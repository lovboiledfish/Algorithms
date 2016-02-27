package solutions;

/**
 * Created by feiyi.zhan on 2/27/16.
 */
public class SearchA2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix != null && matrix.length > 0) {
			// search for row
			int high = _searchRow(matrix, 0, target, false);
			int low = _searchRow(matrix, matrix[0].length - 1, target, true);
			// search in rows
			while (low < high) {
				if (low >= 0 && low < matrix.length && _searchInLine(matrix[low], target)) {
					return true;
				}
				++low;
			}
		}
		return false;
	}

	private int _searchRow(int[][] matrix, int col, int target, boolean left) {
		int lo = 0, hi = matrix.length - 1, ans = left ? -1 : matrix.length;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (target > matrix[mid][col]) {
				if (left) {
					ans = mid;
				}
				lo = mid + 1;
			} else if (target < matrix[mid][col]) {
				if (!left) {
					ans = mid;
				}
				hi = mid - 1;
			} else {
				// target == matrix[mid][col]
				if (left) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}
		}
		return ans;
	}

	private boolean _searchInLine(int[] arr, int target) {
		int lo = 0, hi = arr.length - 1, ans = -1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (target > arr[mid]) {
				ans = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		++ans;
		return ans < arr.length && arr[ans] == target;
	}
}
