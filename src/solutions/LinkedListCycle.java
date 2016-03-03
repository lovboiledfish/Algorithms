package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 3/3/16.
 */
public class LinkedListCycle {
	/**
	 * @param head: The first node of linked list.
	 * @return: True if it has a cycle, or false
	 */
	public boolean hasCycle(ListNode head) {
		ListNode fast = head, slow = head;
		do {
			if (fast == null || fast.next == null) {
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
		} while (fast != slow);
		return true;
	}
}
