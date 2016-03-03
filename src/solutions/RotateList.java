package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/2/16.
 */
public class RotateList {
	/**
	 * @param head: the List
	 * @param k:    rotate to the right k places
	 * @return: the list after rotation
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if (k <= 0 || head == null) {
			return head;
		}

		ListNode left = head, right = head;
		int step = 0;
		while (k > step && right != null) {
			right = right.next;
			++step;
		}
		if (right == null) {
			k %= step;
			return rotateRight(head, k);
		}
		while (right.next != null) {
			left = left.next;
			right = right.next;
		}
		ListNode newHead = left.next;
		left.next = null;
		right.next = head;
		return newHead;
	}
}
