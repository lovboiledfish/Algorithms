package solutions;

import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 1/3/16.
 */
public class CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[][] max1 = _getMax(nums1, k);
        int[][] max2 = _getMax(nums2, k);
        int[] maxNumber = new int[k];
        for (int i = 0; i <= k; ++i) {
            int[] curMax = null;
            if (i - 1 < 0 || i - 1 >= max1.length) {
                if (max2.length == k) curMax = max2[k - 1];
            } else if (k - i - 1 < 0 || k - i - 1 >= max2.length) {
                if (max1.length == k) curMax = max1[k - 1];
            } else curMax = _merge(max1[i - 1], max2[k - i - 1]);
            if (curMax != null) {
                int j = 0;
                for (; j < k && maxNumber[j] == curMax[j]; ++j) ;
                if (j < k && maxNumber[j] < curMax[j]) maxNumber = curMax;
            }
        }
        return maxNumber;
    }

    private int[][] _getMax(int[] nums, int k) {
        k = Math.min(nums.length, k);
        int[][] maxNums = new int[k][];
        if (nums.length == 0) return maxNums;

        maxNums[k - 1] = new int[k];
        int wptr = 0;
        for (int i = 0; i < nums.length && wptr < k; ) {
            int max = -1, idx = -1;
            for (int j = i; j <= nums.length - (k - wptr); ++j) {
                if (nums[j] > max) {
                    max = nums[j];
                    idx = j;
                }
            }
            if (idx == -1) break;
            maxNums[k - 1][wptr++] = max;
            i = idx + 1;
        }
        for (int i = k - 2; i >= 0; --i) {
            maxNums[i] = new int[i + 1];
            int shift = 0;
            for (int j = 0; j <= i; ++j) {
                if (shift == 0 && maxNums[i + 1][j] < maxNums[i + 1][j + 1]) ++shift;
                maxNums[i][j] = maxNums[i + 1][j + shift];
            }
        }
        return maxNums;
    }

    private int[] _merge(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length + nums2.length];
        int ptr1 = 0, ptr2 = 0;
        while (ptr1 < nums1.length || ptr2 < nums2.length) {
            if (ptr2 == nums2.length || (ptr1 < nums1.length && _compare(ptr1, nums1, ptr2, nums2) > 0)) {
                ans[ptr1 + ptr2] = nums1[ptr1];
                ++ptr1;
            } else {
                ans[ptr1 + ptr2] = nums2[ptr2];
                ++ptr2;
            }
        }
        return ans;
    }

    private int _compare(int ptr1, int[] nums1, int ptr2, int[] nums2) {
        while (ptr1 < nums1.length && ptr2 < nums2.length && nums1[ptr1] == nums2[ptr2]) {
            ++ptr1;
            ++ptr2;
        }
        return ((ptr1 < nums1.length)? nums1[ptr1] : -1) - ((ptr2 < nums2.length)? nums2[ptr2] : -1);
    }

    static public class Test {
        static private CreateMaximumNumber _solution = new CreateMaximumNumber();

        static public void test(int[] nums1, int[] nums2, int k) {
            IntStream.of(_solution.maxNumber(nums1, nums2, k)).forEach(i -> System.out.print(i + ", "));
            System.out.println();
        }

        static public void randomTest() {
            int[] nums1 = {3, 4, 6, 5};
            int[] nums2 = {9, 1, 2, 5, 8, 3};
            test(nums1, nums2, 5);

            int[] nums3 = {3, 9};
            int[] nums4 = {8, 9};
            test(nums3, nums4, 3);

            int[] nums5 = {6, 6, 8};
            int[] nums6 = {5, 0, 9};
            test(nums5, nums6, 3);

            int[] nums7 = {6, 7};
            int[] nums8 = {6, 0, 8};
            test(nums7, nums8, 5);

            int[] nums9 = {};
            int[] nums10 = {6, 0, 8};
            test(nums10, nums9, 2);

            int[] nums11 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
            int[] nums12 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
            test(nums11, nums12, 100);
        }
    }
}
