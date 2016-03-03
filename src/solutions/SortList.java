package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/3/16.
 */
public class SortList {
	/**
	 * @param head: The head of linked list.
	 * @return: You should return the head of the sorted linked list,
	using constant space complexity.
	 */
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode head2 = slow.next;
		slow.next = null;
		head = sortList(head);
		head2 = sortList(head2);
		return _merge(head, head2);
	}

	private ListNode _merge(ListNode ptr1, ListNode ptr2) {
		ListNode dummy = new ListNode(-1);
		ListNode wptr = dummy;
		while (ptr1 != null || ptr2 != null) {
			if (ptr2 == null || (ptr1 != null && ptr1.val < ptr2.val)) {
				wptr.next = ptr1;
				ptr1 = ptr1.next;
			} else {
				wptr.next = ptr2;
				ptr2 = ptr2.next;
			}
			wptr = wptr.next;
		}
		return dummy.next;
	}
}
