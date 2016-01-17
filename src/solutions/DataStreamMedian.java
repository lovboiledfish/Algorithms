package solutions;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by PPlovboiledfish on 1/16/16.
 */
public class DataStreamMedian {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        if (nums == null && nums.length == 0)
            return new int[0];
        int[] medians = new int[nums.length];
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        int ptr = 0;
        for (; ptr < nums.length; ++ptr) {
            if (max.isEmpty() || max.peek() > nums[ptr]) {
                max.offer(nums[ptr]);
            } else {
                min.offer(nums[ptr]);
            }
            while (max.size() > min.size() + 1) min.offer(max.poll());
            while (min.size() > max.size() + 1) max.offer(min.poll());
            medians[ptr] = (min.size() > max.size()) ? min.peek() : max.peek();
        }
        return medians;
    }
}
