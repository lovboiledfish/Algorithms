package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.TreeSet;

/**
 * Created by PPlovboiledfish on 2/9/16.
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums != null && nums.length > 1) {
            TreeSet<Long> sorted = new TreeSet<>();
            for (int i = 0; i < nums.length; ++i) {
                if (!sorted.isEmpty()) {
                    Long floor = sorted.floor((long) nums[i]);
                    if (floor != null && Math.abs(floor - nums[i]) <= t) {
                        return true;
                    }
                    Long ceiling = sorted.ceiling((long) nums[i]);
                    if (ceiling != null && Math.abs(ceiling - nums[i]) <= t) {
                        return true;
                    }
                }
                sorted.add((long) nums[i]);
                if (i >= k) {
                    sorted.remove((long) nums[i - k]);
                }
            }
        }
        return false;
    }

    static public class Test {
        static private ContainsDuplicateIII _solution = new ContainsDuplicateIII();

        static public void test(int[] nums, int k, int t, boolean ans) {
            Assert.check(ans == _solution.containsNearbyAlmostDuplicate(nums, k, t));
        }

        static public void randomTest() {
            int[] nums = {1, 3, 8, 5, 11};
            test(nums, 1, 2, true);
        }
    }
}
