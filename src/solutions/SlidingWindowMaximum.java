package solutions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by feiyi.zhan on 1/7/16.
 */
public class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length < k || k == 0)
			return new int[]{};
		Deque<Integer> deque = new ArrayDeque<>();
		int[] res = new int[nums.length - k + 1];
		int i = 0;
		for (; i < nums.length; ++i) {
			while (!deque.isEmpty() && nums[deque.getLast()] < nums[i])
				deque.pollLast();
			deque.addLast(i);
			if (i >= k - 1) {
				while (!deque.isEmpty() && deque.getFirst() < i - k + 1)
					deque.pollFirst();
				res[i - k + 1] = nums[deque.getFirst()];
			}
		}
		return res;
	}
}
