package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/4/16.
 */
public class PartitionList {
	/**
	 * @param head: The first node of linked list.
	 * @param x: an integer
	 * @return: a ListNode
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode dummy1 = new ListNode(-1), dummy2 = new ListNode(-1);
		dummy1.next = head;
		ListNode ptr1 = dummy1, ptr2 = dummy2;
		while (ptr1.next != null) {
			if (ptr1.next.val < x) {
				ptr2.next = ptr1.next;
				ptr2 = ptr2.next;
				ptr1.next = ptr1.next.next;
			} else {
				ptr1 = ptr1.next;
			}
		}
		ptr2.next = dummy1.next;
		return dummy2.next;
	}
}
