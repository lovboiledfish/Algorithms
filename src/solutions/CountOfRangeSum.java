package solutions;

import java.util.Arrays;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 1/15/16.
 */
public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper)
            return 0;

        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; ++i)
            sums[i] = sums[i - 1] + nums[i];
        long[] sortedSums = Arrays.copyOf(sums, sums.length);
        Arrays.sort(sortedSums);

        int[] BIT = new int[nums.length + 1];
        int count = 0;
        for (long sum : sums) {
            if (lower <= sum && sum <= upper) ++count;
            // query [0, r - 1] and [0, l - 1] in sums
            count += _count(BIT, _bisect_right(sortedSums, sum - lower)) -
                    _count(BIT, _bisect_left(sortedSums, sum - upper));
            _addOne(BIT, _bisect_left(sortedSums, sum) + 1);
        }
        return count;
    }

    /**
     * @param arr    - sorted elem holder
     * @param target - value that needs to be inserted
     * @return the leftmost place to insert proposed elem
     */
    private int _bisect_left(long[] arr, long target) {
        if (target > arr[arr.length - 1])
            return arr.length;

        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    /**
     * @param arr    - sorted elem holder
     * @param target - value that needs to be inserted
     * @return the rightmost place to insert proposed elem
     */
    private int _bisect_right(long[] arr, long target) {
        if (target >= arr[arr.length - 1])
            return arr.length;

        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = ((l + r) >> 1) + 1;
            if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        if (arr[r] <= target) ++r;
        return r;
    }

    private void _addOne(int[] BIT, int i) {
        for (; i < BIT.length; i += i & (-i))
            BIT[i] += 1;
    }

    private int _count(int[] BIT, int i) {
        int count = 0;
        for (; i > 0; i -= i & (-i))
            count += BIT[i];
        return count;
    }

    static public class Test {
        static private CountOfRangeSum _solution = new CountOfRangeSum();

        static public void test(int[] nums, int lower, int upper, int ans) {
            Assert.check(ans == _solution.countRangeSum(nums, lower, upper));
        }

        static public void randomTest() {
            test(new int[]{-2,0,-2,-3,2,2,1,-3,4}, 4, 11, 5);
        }
    }
}
