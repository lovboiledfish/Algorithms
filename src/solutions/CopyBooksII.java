package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Arrays;

/**
 * Created by PPlovboiledfish on 2/6/16.
 */
public class CopyBooksII {
    /**
     * @param n:     an integer
     * @param times: an array of integers
     * @return: an integer
     */
    public int copyBooksII(int n, int[] times) {
        if (times == null || times.length == 0)
            return 0;

        final int m = times.length;
        int[][] minTime = new int[2][n + 1];
        Arrays.fill(minTime[0], Integer.MAX_VALUE);
        for (int k = 0; k <= n; ++k) {
            minTime[1][k] = k * times[0];
        }
        for (int i = 1; i < m; ++i) {
            int k = 1;
            int j = 0;
            while (k <= n) {
                if (j <= k && minTime[i % 2][k - j] > j * times[i]) {
                    minTime[(i + 1) % 2][k] = Math.min(minTime[(i + 1) % 2][k], minTime[i % 2][k - j]);
                    ++j;
                } else {
                    minTime[(i + 1) % 2][k] = Math.min(minTime[(i + 1) % 2][k], j * times[i]);
                    ++k;
                    if (j > 0) {
                        --j;
                    }
                }
            }
        }
        return minTime[m % 2][n];
    }

    static public class Test {
        static private CopyBooksII _solution = new CopyBooksII();

        static public void test(int n, int[] times, int ans) {
            Assert.check(_solution.copyBooksII(n, times) == ans);
        }

        static public void randomTest() {
            int[] times = {274, 233, 513, 981, 372, 328, 251, 926, 363, 542, 573, 966, 846, 940, 848, 22, 804, 881, 492,
                    151, 583, 371, 403, 135, 571, 37, 647, 517, 929, 915, 500, 372, 763, 94, 167, 100, 53, 679, 878, 463,
                    310, 568, 689, 631, 817, 820, 967, 40, 371, 589, 787, 891, 304, 496, 419, 64, 791, 768, 330, 678, 15,
                    2, 924, 958, 583, 919, 750, 63, 435, 858, 478, 221, 752, 238, 410, 510, 56, 475, 749, 329, 633, 844,
                    111, 978, 282, 561, 91, 327, 298, 301, 367, 402, 805, 50, 962, 91, 155, 84, 955, 280};
            test(53, times, 75);
        }
    }
}
