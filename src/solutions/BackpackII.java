package solutions;

/**
 * Created by PPlovboiledfish on 2/4/16.
 */
public class BackpackII {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        if (A == null || A.length == 0)
            return 0;

        int n = A.length;
        int[][] maxValue = new int[2][m + 1];
        int cur = 0;
        for (int j = 0; j < n; ++j, cur = 1 - cur) {
            for (int k = 0; k < m + 1; ++k) {
                maxValue[cur][k] = maxValue[1 - cur][k];
                if (k >= A[j]) {
                    maxValue[cur][k] = Math.max(maxValue[cur][k], maxValue[1 - cur][k - A[j]] + V[j]);
                }
            }
        }
        return maxValue[1 - cur][m];
    }
}
