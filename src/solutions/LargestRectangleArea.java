package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by PPlovboiledfish on 10/31/15.
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;

        int i;
        for (i = 0; i < height.length; ++i) {
            if (stack.empty() || height[i] > height[stack.peek()])
                stack.push(i);
            else {
                int tmp = stack.pop();
                maxArea = Math.max(maxArea, height[tmp]*(stack.empty()? i : i-stack.peek()-1));
                i--;
            }
        }
        while (!stack.empty()) {
            int tmp = stack.pop();
            maxArea = Math.max(maxArea, height[tmp]*(stack.empty()? i : i-stack.peek()-1));
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
