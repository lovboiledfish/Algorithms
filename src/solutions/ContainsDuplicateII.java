package solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by PPlovboiledfish on 2/9/16.
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums != null && nums.length > 1) {
            Set<Integer> vals = new HashSet<>();
            for (int i = 0; i < nums.length; ++i) {
                if (vals.contains(nums[i])) {
                    return true;
                }
                vals.add(nums[i]);
                if (i >= k) {
                    vals.remove(nums[i - k]);
                }
            }
        }
        return false;
    }
}
