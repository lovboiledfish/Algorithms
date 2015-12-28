package solutions;

import solutions.utils.ListNode;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode reversed = reverseList(head.next);
        ListNode prev = head.next;
        head.next = null;
        prev.next = head;

        return reversed;
    }

    static public class Test {
        static private ReverseLinkedList _solution = new ReverseLinkedList();

        static public void test(ListNode head) {
            ListNode reversed = _solution.reverseList(head);
            ListNode cur = reversed;
            while (cur != null) {
                System.out.print(cur.val+" -> ");
                cur = cur.next;
            }
            System.out.println("NULL");
        }

        static public void randomTest() {
            ListNode head = new ListNode(0);
            head.next = new ListNode(1);
            head.next.next = new ListNode(2);
            head.next.next.next = new ListNode(3);
            test(head);
        }
    }
}
