package solutions;

import solutions.utils.ListNode;
import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/2/16.
 */
public class ConvertSortedListToBalancedBST {
	/**
	 * @param head: The first node of linked list.
	 * @return: a tree node
	 */
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode fast = head, ptr = head, prev = null;
		while (fast.next != null && fast.next.next != null) {
			prev = ptr;
			ptr = ptr.next;
			fast = fast.next.next;
		}

		TreeNode root = new TreeNode(ptr.val);
		if (prev != null) {
			prev.next = null;
			root.left = sortedListToBST(head);
			prev.next = ptr;
		}
		root.right = sortedListToBST(ptr.next);
		return root;
	}
}
