package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 1/10/16.
 */
public class TriangleCount {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        if (S == null || S.length < 3)
            return 0;
        Arrays.sort(S);
        int count = 0;
        for (int i = S.length - 1; i > 1; --i)
            count += _triangleCount(S, i - 1, S[i]);
        return count;
    }

    private int _triangleCount(int S[], int rangeLimit, int target) {
        int lower = 0, upper = rangeLimit, count = 0;
        while (lower < upper) {
            if (S[lower] + S[upper] > target) {
                count += upper - lower;
                --upper;
            } else {
                ++lower;
            }
        }
        return count;
    }

    static public class Test {
        static private TriangleCount _solution = new TriangleCount();

        static public void test(int S[], int ans) {
            Assert.check(ans == _solution.triangleCount(S));
        }

        static public void randomTest() {
            test(new int[] {3, 4, 6, 7}, 3);
            test(new int[] {4, 4, 4, 4}, 4);
        }
    }
}
