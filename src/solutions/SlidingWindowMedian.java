package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/16/16.
 */
public class SlidingWindowMedian {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (nums == null || k > nums.length)
            return res;

        final int[] numsRef = nums;
        Comparator<Integer> comparator = (o1, o2) -> numsRef[o1] - numsRef[o2];
        PriorityQueue<Integer> min = new PriorityQueue<>(k, comparator);
        PriorityQueue<Integer> max = new PriorityQueue<>(k, Collections.reverseOrder(comparator));
        for (int i = 0; i < nums.length; ++i) {
            if (max.isEmpty() || nums[max.peek()] > nums[i]) {
                max.offer(i);
            } else {
                min.offer(i);
            }
            if (i >= k) {
                if (max.contains(i - k))
                    max.remove(i - k);
                else
                    min.remove(i - k);
            }
            while (max.size() > min.size() + 1) {
                int swp = max.poll();
                min.offer(swp);
            }
            while (min.size() > max.size() + 1) {
                int swp = min.poll();
                max.offer(swp);
            }
            if (i >= k - 1) {
                if (min.size() == max.size())
                    res.add((nums[min.peek()] + nums[max.peek()]) >> 1);
                else
                    res.add(min.size() > max.size() ? nums[min.peek()] : nums[max.peek()]);
            }
        }
        return res;
    }

    static public class Test {
        static private SlidingWindowMedian _solution = new SlidingWindowMedian();

        static public void test(int[] nums, int k) {
            _solution.medianSlidingWindow(nums, k).stream().map(i -> i + ", ").forEach(System.out::print);
        }

        static public void randomTest() {
            test(new int[]{1, 2, 7, 7, 2}, 3);
        }
    }
}
