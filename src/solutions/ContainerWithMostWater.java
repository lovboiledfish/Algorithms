package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;

        int l = 0, r = heights.length - 1, height = 0, maxArea = 0;
        while (l < r) {
            height = Math.min(heights[l], heights[r]);
            maxArea = Math.max(maxArea, (r - l) * height);
            if (heights[l] > heights[r]) {
                --r;
            } else {
                ++l;
            }
        }
        return maxArea;
    }
}
