package solutions;

import solutions.utils.ListNode;

/**
 * Created by PPlovboiledfish on 1/30/16.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode(-1);
        ListNode wptr = sum;
        int carrier = 0;
        while (l1 != null || l2 != null || carrier > 0) {
            int l = 0;
            if (l1 != null) {
                l = l1.val;
                l1 = l1.next;
            }
            int r = 0;
            if (l2 != null) {
                r = l2.val;
                l2 = l2.next;
            }

            int s = l + r + carrier;
            carrier = s / 10;
            s = s % 10;

            wptr.next = new ListNode(s);
            wptr = wptr.next;
        }
        return sum.next;
    }
}
