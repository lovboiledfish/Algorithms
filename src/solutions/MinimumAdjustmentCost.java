package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 2/5/16.
 */
public class MinimumAdjustmentCost {
    /**
     * @param A:      An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        if (A == null || A.size() < 2)
            return 0;

        int maxDiff = 0;
        int max = A.get(0);
        int min = A.get(0);
        for (int i = 1; i < A.size(); ++i) {
            maxDiff = Math.max(maxDiff, Math.abs(A.get(i) - A.get(i - 1)));
            max = Math.max(max, A.get(i));
            min = Math.min(min, A.get(i));
        }
        if (maxDiff <= target)
            return 0;

        int[][] minCost = new int[2][max - min + 1];
        for (int j = 0; j < minCost[0].length; ++j) {
            minCost[0][j] = Math.abs(j + min - A.get(0));
        }
        int cur = 1;
        for (int i = 1; i < A.size(); ++i, cur = 1- cur) {
            for (int j = 0; j < minCost[0].length; ++j) {
                int m = Integer.MAX_VALUE;
                for (int k = Math.max(0, j - target); k <= Math.min(minCost[0].length - 1, j + target); ++k) {
                    m = Math.min(m, minCost[1 - cur][k]);
                }
                minCost[cur][j] = m + Math.abs(j + min - A.get(i));
            }
        }
        int m = Integer.MAX_VALUE;
        for (int k = 0; k < minCost[0].length; ++k) {
            m = Math.min(m, minCost[1 - cur][k]);
        }
        return m;
    }

    static public class Test {
        static private MinimumAdjustmentCost _solution = new MinimumAdjustmentCost();

        static public void test(ArrayList<Integer> A, int target, int ans) {
            Assert.check(_solution.MinAdjustmentCost(A, target) == ans);
        }

        static public void randomTest() {
            ArrayList<Integer> A = new ArrayList<>(Arrays.asList(20, 25, 35, 45, 55, 65, 75, 85, 25, 35, 45, 55, 15, 15, 15, 7, 2, 11, 15, 11, 15));
            test(A, 10, 90);
        }
    }
}
