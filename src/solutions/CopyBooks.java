package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 2/6/16.
 */
public class CopyBooks {
    /**
     * @param pages: an array of integers
     * @param k:     an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0)
            return 0;

        final int n = pages.length;
        k = Math.min(n, k);
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] += preSum[i] + pages[i];
        }

        int[][] minTime = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(minTime[i], Integer.MAX_VALUE);
            minTime[i][1] = preSum[i];
            int kk = 2;
            int j = 1;
            while (kk <= k) {
                minTime[i][kk] = Math.min(minTime[i][kk], Math.max(minTime[j][kk - 1], preSum[i] - preSum[j]));
                if (minTime[j][kk - 1] < preSum[i] - preSum[j]) {
                    ++j;
                } else {
                    ++kk;
                    if (j > 1) {
                        --j;
                    }
                }
            }
        }
        return minTime[n][k];
    }

    static public class Test {
        static private CopyBooks _solution = new CopyBooks();

        static public void test(int[] pages, int k, int ans) {
            Assert.check(_solution.copyBooks(pages, k) == ans);
        }

        static public void randomTest() {
            int[] pages = {1, 2};
            test(pages, 5, 2);
        }
    }
}
