package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/3/16.
 */
public class ReorderList {
	/**
	 * @param head: The head of linked list.
	 * @return: void
	 */
	public void reorderList(ListNode head) {
		if (head == null) {
			return;
		}

		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode ptr1 = head, ptr2 = slow.next;
		slow.next = null;
		ptr2 = _reverse(ptr2);
		while (ptr1 != null && ptr2 != null) {
			ListNode toInsert = ptr2;
			ptr2 = ptr2.next;
			toInsert.next = ptr1.next;
			ptr1.next = toInsert;
			ptr1 = toInsert.next;
		}
	}

	private ListNode _reverse(ListNode head) {
		if (head != null) {
			ListNode dummy = new ListNode(-1);
			dummy.next = head;
			ListNode ptr = head.next;
			while (ptr != null) {
				ListNode insert = ptr;
				ptr = ptr.next;
				head.next = ptr;
				insert.next = dummy.next;
				dummy.next = insert;
			}
			return dummy.next;
		}
		return null;
	}
}
