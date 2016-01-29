package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int interval = (max - min) / nums.length + 1;
        int count = (max - min) / interval + 1;
        int[][] holes = new int[count][];
        for (int num : nums) {
            int hole = (num - min) / interval;
            if (holes[hole] == null) {
                holes[hole] = new int[]{num, num};
            } else {
                holes[hole][0] = Math.min(holes[hole][0], num);
                holes[hole][1] = Math.max(holes[hole][1], num);
            }
        }

        int maxGap = 0, last = -1;
        for (int i = 0; i < count; ++i) {
            if (holes[i] != null) {
                if (last >= 0) {
                    maxGap = Math.max(maxGap, holes[i][0] - last);
                }
                last = holes[i][1];
            }
        }
        return maxGap;
    }
}
