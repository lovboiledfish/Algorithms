package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 2/12/16.
 */
public class OddEvenLinkedList {
	public ListNode oddEvenList(ListNode head) {
		if (head != null && head.next != null && head.next.next != null) {
			ListNode wptr = head, prev = head.next, ptr = head.next.next;
			while (ptr != null) {
				if (wptr.next != ptr) {
					prev.next = ptr.next;
					ListNode tmp = wptr.next;
					wptr.next = ptr;
					ptr.next = tmp;
					prev = prev.next;
				} else {
					prev = ptr.next;
				}
				if (prev == null) {
					break;
				}
				ptr = prev.next;
				wptr = wptr.next;
			}
		}
		return head;
	}
}
