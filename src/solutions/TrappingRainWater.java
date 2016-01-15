package solutions;

/**
 * Created by PPlovboiledfish on 1/14/16.
 */
public class TrappingRainWater {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length < 2)
            return 0;

        int left = 0, right = heights.length - 1, curHeight = 0, vol = 0;
        while (left < right) {
            int wall = Math.min(heights[left], heights[right]);
            if (curHeight < wall) {
                curHeight = wall;
            } else {
                vol += curHeight - wall;
            }
            if (heights[left] == wall) {
                ++left;
            } else {
                --right;
            }
        }
        return vol;
    }
}
