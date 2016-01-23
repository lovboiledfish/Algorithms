package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/23/16.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int[] heights = new int[matrix[0].length + 1];
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j <= matrix[0].length; ++j) {
                heights[j] = (j < matrix[0].length && matrix[i][j] == '1') ? heights[j] + 1 : 0;
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[j])
                    maxArea = Math.max(maxArea, heights[stack.pop()] * (j - (stack.isEmpty() ? 0 : (stack.peek() + 1))));
                if (stack.isEmpty() || heights[stack.peek()] < heights[j])
                    stack.push(j);
            }
            stack.clear();
        }
        return maxArea;
    }

    static public class Test {
        static private MaximalRectangle _solution = new MaximalRectangle();

        static public void test(char[][] matrix, int ans) {
            int ret = _solution.maximalRectangle(matrix);
            Assert.check(ret == ans);
        }

        static public void randomTest() {
            char[][] matrix = {
                    "101101".toCharArray(),
                    "111111".toCharArray(),
                    "011011".toCharArray(),
                    "111010".toCharArray(),
                    "011111".toCharArray(),
                    "110111".toCharArray()
            };
            test(matrix, 8);

            char[][] matrix1 = {
                    "10".toCharArray(),
                    "10".toCharArray(),
            };
            test(matrix1, 2);

            char[][] matrix2 = {
                    "00010111".toCharArray(),
                    "01100101".toCharArray(),
                    "10111101".toCharArray(),
                    "00010000".toCharArray(),
                    "00100010".toCharArray(),
                    "11100111".toCharArray(),
                    "10011001".toCharArray(),
                    "01001100".toCharArray(),
                    "10010000".toCharArray()
            };
            test(matrix2, 4);
        }
    }
}
