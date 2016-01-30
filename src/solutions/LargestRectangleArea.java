package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by PPlovboiledfish on 10/31/15.
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        for (int i = 0; i <= height.length; ++i) {
            int h = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && height[stack.peek()] > h) {
                int top = stack.pop();
                int margin = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - margin - 1) * height[top]);
            }
            stack.push(i);
        }
        return maxArea;
    }

    static public class TestLargestRectangleArea {
        static private LargestRectangleArea _solution = new LargestRectangleArea();

        static public void randomBars() {
            int[] height = {2, 1, 5, 6, 2, 3};
            test(height, 10);
        }

        static public void twoOneTwo() {
            int[] height = {2, 1, 2};
            test(height, 3);
        }

        static public void test(int[] heights, int ans) {
            int ret = _solution.largestRectangleArea(heights);
            System.out.println(ret);
            Assert.check(ans == ret);
        }
    }

}
