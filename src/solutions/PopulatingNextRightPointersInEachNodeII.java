package solutions;

import solutions.utils.TreeLinkNode;

/**
 * Created by feiyi.zhan on 2/13/16.
 */
public class PopulatingNextRightPointersInEachNodeII {
	public void connect(TreeLinkNode root) {
		TreeLinkNode parent = root;
		final TreeLinkNode dummy = new TreeLinkNode(-1);
		while (parent != null) {
			TreeLinkNode wptr = dummy;
			while (parent != null) {
				if (parent.left != null) {
					wptr.next = parent.left;
					wptr = wptr.next;
				}
				if (parent.right != null) {
					wptr.next = parent.right;
					wptr = wptr.next;
				}
				parent = parent.next;
			}
			parent = dummy.next;
			dummy.next = null;
		}
	}
}
