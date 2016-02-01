package solutions;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 1/31/16.
 */
public class TheSmallestDifference {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        int minDiff = Integer.MAX_VALUE;
        if (A != null && A.length > 0 && B != null && B.length > 0) {
            Arrays.sort(A);
            Arrays.sort(B);
            for (int iA = 0, iB = 0; iA < A.length && iB < B.length; ++iA) {
                while (iB < B.length && B[iB] < A[iA]) {
                    ++iB;
                }
                if (iB > 0) {
                    minDiff = Math.min(minDiff, Math.abs(A[iA] - B[iB - 1]));
                }
                if (iB < B.length) {
                    minDiff = Math.min(minDiff, Math.abs(A[iA] - B[iB]));
                }
            }
        }
        return minDiff;
    }
}
