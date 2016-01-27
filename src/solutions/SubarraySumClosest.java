package solutions;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by PPlovboiledfish on 1/26/16.
 */
public class SubarraySumClosest {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] ret = {-1, -1};
        if (nums != null && nums.length > 0) {
            int delta = Integer.MAX_VALUE;
            int sum = 0;
            TreeMap<Integer, Integer> sums = new TreeMap<Integer, Integer>();
            sums.put(0, -1);
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                delta = _record(sum, sums.floorEntry(sum), i, ret, delta);
                delta = _record(sum, sums.ceilingEntry(sum), i, ret, delta);
                if (delta == 0)
                    break;
                sums.put(sum, i);
            }
        }
        return ret;
    }

    private int _record (int sum, Map.Entry<Integer, Integer> registry, int end, int[] ret, int delta) {
        if (registry != null) {
            if (delta >= Math.abs(sum - registry.getKey())) {
                delta = Math.abs(sum - registry.getKey());
                ret[0] = registry.getValue() + 1;
                ret[1] = end;
            }
        }
        return delta;
    }
}
