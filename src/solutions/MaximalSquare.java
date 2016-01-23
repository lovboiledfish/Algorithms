package solutions;

import com.sun.tools.javac.util.Assert;
import solutions.utils.MyMath;

/**
 * Created by PPlovboiledfish on 10/31/15.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int glodbalMaxSquare = 0;
        int[][] maxSquare = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j= 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] != '1')
                    maxSquare[i][j] = 0;
                else {
                    if (i == 0 || j == 0)
                        maxSquare[i][j] = 1;
                    else
                        maxSquare[i][j] = MyMath.min(maxSquare[i - 1][j - 1], maxSquare[i][j - 1], maxSquare[i - 1][j]) + 1;
                }
                glodbalMaxSquare = (glodbalMaxSquare > maxSquare[i][j])? glodbalMaxSquare : maxSquare[i][j];
            }
        }

        return glodbalMaxSquare * glodbalMaxSquare;
    }

    static public class Test {
        static private MaximalSquare _solution = new MaximalSquare();

        static public void test(char[][] matrix, int ans) {
            int ret = _solution.maximalSquare(matrix);
            Assert.check(ret == ans);
        }

        static public void randomTest() {
            char[][] matrix = {
                    {'1', '0', '1', '0', '0'},
                    {'1', '0', '1', '1', '1'},
                    {'1', '1', '1', '1', '1'},
                    {'1', '0', '0', '1', '0'}
            };

            test(matrix, 4);
        }

        static public void failure1() {
            char[][] matrix = {
                    {'0', '0', '0'},
                    {'0', '0', '0'},
                    {'0', '0', '0'},
                    {'0', '0', '0'}
            };

            test(matrix, 0);
        }

        static public void failure2() {
            char[][] matrix = {{'1'}};

            test(matrix, 1);
        }
    }
}
