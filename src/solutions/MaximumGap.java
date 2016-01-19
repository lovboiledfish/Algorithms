package solutions;

/**
 * Created by PPlovboiledfish on 1/18/16.
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int period = (max - min) / nums.length + 1;
        int[][] holes = new int[(max - min) / period + 1][3];
        for (int num : nums) {
            int hole = (num - min) / period;
            if (holes[hole][2] == 0) {
                holes[hole][0] = num;
                holes[hole][1] = num;
            } else {
                holes[hole][0] = Math.max(num, holes[hole][0]);
                holes[hole][1] = Math.min(num, holes[hole][1]);
            }
            ++holes[hole][2];
        }
        int maxGap = 0, lastElem = min;
        for (int[] hole : holes) {
            if (hole[2] != 0) {
                maxGap = Math.max(maxGap, hole[1] - lastElem);
                lastElem = hole[0];
            }
        }
        return maxGap;
    }
}
