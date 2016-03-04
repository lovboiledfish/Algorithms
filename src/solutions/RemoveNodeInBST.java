package solutions;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/4/16.
 */
public class RemoveNodeInBST {
	/**
	 * @param root: The root of the binary search tree.
	 * @param value: Remove the node with given value.
	 * @return: The root of the binary search tree after removal.
	 */
	public TreeNode removeNode(TreeNode root, int value) {
		TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
		TreeNode parent = dummy, ptr = root;
		parent.left = root;
		while (ptr != null && value != ptr.val) {
			parent = ptr;
			if (value < ptr.val) {
				ptr = ptr.left;
			} else {
				ptr = ptr.right;
			}
		}
		if (ptr != null) {
			if (ptr.left != null) {
				TreeNode prev = null, p = ptr.left;
				while (p.right != null) {
					prev = p;
					p = p.right;
				}
				if (prev != null) {
					prev.right = p.left;
				} else {
					ptr.left = ptr.left.left;
				}
				ptr.val = p.val;
			} else if (ptr.right != null) {
				TreeNode prev = null, p = ptr.right;
				while (p.left != null) {
					prev = p;
					p = p.left;
				}
				if (prev != null) {
					prev.left = p.right;
				} else {
					ptr.right = ptr.right.right;
				}
				ptr.val = p.val;
			} else {
				if (parent.left == ptr) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
		}
		return dummy.left;
	}
}
