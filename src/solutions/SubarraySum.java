package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PPlovboiledfish on 1/26/16.
 */
public class SubarraySum {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (nums != null && nums.length > 0) {
            Map<Integer, Integer> sums = new HashMap<Integer, Integer>();
            sums.put(0, -1);
            int sum = 0;
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                if (sums.containsKey(sum)) {
                    ret.add(sums.get(sum) + 1);
                    ret.add(i);
                    break;
                }
                sums.put(sum, i);
            }
        }
        return ret;
    }
}
