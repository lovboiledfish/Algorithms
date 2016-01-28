package solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by feiyi.zhan on 1/27/16.
 */
public class KthSmallestNumberInSortedMatrix {
	/**
	 * @param matrix: a matrix of integers
	 * @param k: an integer
	 * @return: the kth smallest number in the matrix
	 */
	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		final int[][] refMatrix = matrix;
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((Comparator<int[]>) (a1, a2) -> refMatrix[a1[0]][a1[1]] - refMatrix[a2[0]][a2[1]]);
		for (int i = 0; i < matrix.length; ++i) {
			minHeap.offer(new int[]{i, 0});
		}
		int kth = 0;
		while (!minHeap.isEmpty() && k > 0) {
			int[] arr = minHeap.poll();
			kth = matrix[arr[0]][arr[1]];
			arr[1] += 1;
			if (arr[1] < matrix[0].length) {
				minHeap.offer(arr);
			}
			--k;
		}
		return k == 0 ? kth : -1;
	}
}
