package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Online assessment problem I
 * <p>
 * Friend Cycle
 * <p>
 * Given an N*N char matrix, cell[i][j] is 'Y' when person No.i and No. j are friends.
 * Find the number of friend groups.
 * <p>
 * Created by PPlovboiledfish on 2/12/16.
 */
public class FriendCycle {
    public int numOfGroups(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        final int n = matrix.length;
        int[] ids = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            ids[i] = i;
        }
        int cntGroups = n;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (matrix[i][j] == 'Y' && _union(ids, i + 1, j + 1)) {
                    --cntGroups;
                }
            }
        }
        return cntGroups;
    }

    private int _find(int[] ids, int i) {
        while (ids[i] != i) {
            i = ids[i];
        }
        return i;
    }

    private boolean _union(int[] ids, int i, int j) {
        i = _find(ids, i);
        j = _find(ids, j);
        if (i > j) {
            ids[i] = j;
        } else {
            ids[j] = i;
        }
        return i != j;
    }

    static public class Test {
        static private FriendCycle _solution = new FriendCycle();

        static public void test(char[][] matrix, int ans) {
            Assert.check(_solution.numOfGroups(matrix) == ans);
        }

        static public void randomTest() {
            char[][] matrix = {
                    "YX".toCharArray(),
                    "XY".toCharArray()
            };
            test(matrix, 2);
        }
    }
}
