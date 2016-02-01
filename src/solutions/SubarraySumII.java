package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 1/31/16.
 */
public class SubarraySumII {
    /**
     * @param A     an integer array
     * @param start an integer
     * @param end   an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0 || start > end)
            return 0;

        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; ++i) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        Arrays.sort(preSum);
        int cnt = 0;
        for (int i = 0; i < A.length; ++i) {
            int left = _binarySearchRight(preSum, preSum[i] + start - 1);
            if (left == preSum.length)
                break;
            int right = _binarySearchRight(preSum, preSum[i] + end);
            --right;
            cnt += (right - left + 1);
        }
        return cnt;
    }

    private int _binarySearchRight(int[] arr, int target) {
        if (target > arr[arr.length - 1])
            return arr.length;
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = ((l + r) >> 1) + 1;
            if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        if (arr[r] <= target) ++r;
        return r;
    }

    static public class Test {
        static private SubarraySumII _solution = new SubarraySumII();

        static public void test(int[] A, int start, int end, int ans) {
            Assert.check(ans == _solution.subarraySumII(A, start, end));
        }

        static public void randomTest() {
            int[] A = {1, 2, 3, 4};
            test(A, 1, 3, 4);
        }
    }
}
