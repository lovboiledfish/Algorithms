package solutions;

/**
 * Created by PPlovboiledfish on 1/23/16.
 */
public class LongestIncreasingContinuousSubsequence {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length < 0)
            return 0;

        int trend = 0;// 0 - no trend, 1 - increasing, -1 - decreasing
        int start = 0, maxLength = 0;
        for (int i = 1; i < A.length; ++i) {
            if ((A[i] - A[i - 1]) * trend <= 0) {
                if (A[i] == A[i - 1]) {
                    trend = 0;
                    continue;
                }

                trend = (A[i] > A[i - 1]) ? 1 : -1;
                maxLength = Math.max(maxLength, i - start);
                start = i - 1;
            }
        }
        return Math.max(maxLength, A.length - start);
    }
}
