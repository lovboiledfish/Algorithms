package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/3/16.
 */
public class RemoveDuplicatesFromSortedList {
	/**
	 * @param head is the head of the linked list
	 * @return: ListNode head of linked list
	 */
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);
		ListNode wptr = dummy;
		ListNode ptr = head;
		int prevVal = Integer.MIN_VALUE;
		while (ptr != null) {
			if (prevVal != ptr.val) {
				wptr.next = ptr;
				wptr = wptr.next;
				prevVal = ptr.val;
			}
			ptr = ptr.next;
		}
		wptr.next = null;
		return dummy.next;
	}
}
