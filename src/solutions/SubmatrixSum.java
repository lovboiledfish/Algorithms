package solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feiyi.zhan on 1/27/16.
 */
public class SubmatrixSum {
	/**
	 * @param matrix an integer matrix
	 * @return the coordinate of the left-up and right-down number
	 */
	public int[][] submatrixSum(int[][] matrix) {
		int[][] coords = new int[2][];
		if (matrix != null && matrix.length > 0) {
			int[][] sums = new int[matrix.length][matrix[0].length];
			for (int i = 0; i < matrix.length; ++i) {
				int lineSum = 0;
				for (int j = 0; j < matrix[0].length; ++j) {
					lineSum += matrix[i][j];
					sums[i][j] = (i > 0 ? sums[i - 1][j] : 0) + lineSum;
				}
			}

			for (int i = 0; i < matrix.length; ++i) {
				for (int k = -1; k < i; ++k) {
					Map<Integer, Integer> cache = new HashMap<>();
					cache.put(0, -1);
					for (int j = 0; j < matrix[0].length; ++j) {
						int area = sums[i][j] - (k < 0 ? 0 : sums[k][j]);
						if (!cache.containsKey(area)) {
							cache.put(area, j);
						} else {
							int l = cache.get(area);
							coords[0] = new int[]{k + 1, l + 1};
							coords[1] = new int[]{i, j};
							return coords;
						}
					}
				}
			}
		}
		return coords;
	}
}
