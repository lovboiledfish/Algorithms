package solutions;

import solutions.utils.ListNode;

/**
 * Created by feiyi.zhan on 2/14/16.
 */
public class IntersectionOfTwoLinkedList {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode itA = headA, itB = headB;
		while (itA != itB) {
			itA = itA.next;
			itB = itB.next;
			if (itA == itB) {
				return itA;
			}
			if (itA == null) {
				itA = headB;
			}
			if (itB == null) {
				itB = headA;
			}
		}
		return itA;
	}
}
