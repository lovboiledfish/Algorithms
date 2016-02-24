package solutions;

/**
 * Created by feiyi.zhan on 2/24/16.
 */
public class SearchA2DMatrix {
	/**
	 * @param matrix, a list of lists of integers
	 * @param target, an integer
	 * @return a boolean, indicate whether matrix contains target
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}

		// search col
		int up = 0, down = matrix.length - 1;
		int col = up - 1;
		while (up <= down) {
			int mid = up + ((down - up) >> 1);
			if (matrix[mid][0] == target) {
				return true;
			} else if (matrix[mid][0] > target) {
				down = mid - 1;
			} else {
				col = mid;
				up = mid + 1;
			}
		}
		if (col < 0 || col >= matrix.length) {
			return false;
		}

		// search row
		int left = 0, right = matrix[0].length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (matrix[col][mid] == target) {
				return true;
			} else if (matrix[col][mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}
}
