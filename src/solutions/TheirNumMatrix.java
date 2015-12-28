package solutions;

/**
 * Created by PPlovboiledfish on 12/10/15.
 */
public class TheirNumMatrix {

    int[][] tree;
    int[][] nums;
    int m;
    int n;

    public TheirNumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        tree = new int[m+1][n+1];
        nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return 0;
        return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
    }

    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    static public class Test {
        static public void randomTest() {
            int[][] arr = {
                    {3, 6, 0, 7, 8, 1},
                    {0, 9, 2, 7, 0, 5},
                    {2, 1, 2, 4, 8, 9},
                    {8, 7, 6, 3, 3, 2},
                    {6, 1, 9, 9, 0, 7},
                    {7, 6, 8, 4, 5, 1},
            };

            NumMatrix segTree = new NumMatrix(arr);

            System.out.println(segTree.sumRegion(0, 0, 2, 2));
            System.out.println(segTree.sumRegion(3, 3, 5, 5));
            System.out.println(segTree.sumRegion(0, 3, 2, 5));
            System.out.println(segTree.sumRegion(3, 0, 5, 2));
            System.out.println(segTree.sumRegion(0, 0, 5, 5));

            segTree.update(3, 4, 6);
            System.out.println(segTree.sumRegion(0, 0, 2, 2));
            System.out.println(segTree.sumRegion(3, 3, 5, 5));
            System.out.println(segTree.sumRegion(0, 3, 2, 5));
            System.out.println(segTree.sumRegion(3, 0, 5, 2));
            System.out.println(segTree.sumRegion(0, 0, 5, 5));

            int[][] arr2 = {
                    {3, 0, 1, 4, 2},
                    {5, 6, 3, 2, 1},
                    {1, 2, 0, 1, 5},
                    {4, 1, 0, 1, 7},
                    {1, 0, 3, 0, 5},
            };
            segTree = new NumMatrix(arr2);
            segTree.sumRegion(2, 1, 4, 3);
            segTree.update(3, 2, 2);
            segTree.sumRegion(2, 1, 4, 3);

            int[][] arr3 = {{0, -5, 9, 1, -8, 5, 8, 1, 1, 5}};
            segTree = new NumMatrix(arr3);
            segTree.sumRegion(0, 5, 0, 9);
            segTree.update(0, 3, -1);
            segTree.sumRegion(0, 3, 0, 6);

            int[][] arr4 = {
                    {2, 1, 8, -3, -6, 1, -3, 5, 3, 8, -6, 0, -6},
                    {0, -2, -9, 0, 2, 3, -7, -2, -1, 2, -4, -1, 4},
                    {3, 2, 7, 0, 1, 8, -1, 0, 6, -2, -7, -3, 3},
                    {2, 9, -8, 5, 4, 4, 1, -6, 4, -3, 4, -7, 0},
                    {1, 0, 1, 5, 2, 4, -4, -6, -1, 8, 6, 5, 5},
                    {-6, -2, -1, -2, -4, -2, 9, -5, -9, -5, -1, 5, 6},
                    {-8, 6, -3, -4, 9, -1, -3, -6, 8, 3, -5, 9, 7},
                    {-1, -7, 4, -3, 7, 3, -7, 4, -4, 1, -6, 5, -6},
                    {7, 8, 6, -7, 2, 2, -9, 0, 9, -7, -6, -4, -5},
                    {4, 8, 3, 9, 2, 9, 5, 1, -5, 7, 6, 1, 8},
                    {5, 9, 3, 9, 3, 8, 2, 4, -8, -1, -8, 2, 3},
                    {-9, -6, 6, 4, 4, -1, 8, 2, 3, -4, 0, 2, -4},
                    {4, -8, -6, 3, -2, 3, 4, 5, 3, -6, 2, -8, -7},
                    {-8, 4, 1, 4, 4, 5, 0, -5, 4, 6, -1, 0, 3},
                    {3, -2, 8, 4, 1, -5, 1, -6, -8, 8, 2, 4, 1},
                    {-1, 8, -5, -9, -5, 8, -9, 2, 7, 2, -3, -5, -9},
                    {8, -5, -6, -2, 4, 0, -3, -5, 2, 2, -3, 4, 5},
                    {0, 9, 6, -3, -9, 0, -7, -3, -9, -5, -6, 9, -2}
            };
            NumMatrix sol = new NumMatrix(arr4);
            sol.update(13,9,-6);
            sol.sumRegion(9,0,12,5);
            sol.sumRegion(11,3,14,12);
            sol.update(0,2,9);
        }
    }
}
