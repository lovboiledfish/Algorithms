package solutions;

import solutions.utils.ListNode;

/**
 * Created by PPlovboiledfish on 2/7/16.
 */
public class LinkedListCycleII {
    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins.
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next;
        } while (slow != fast);

        slow = dummy;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
