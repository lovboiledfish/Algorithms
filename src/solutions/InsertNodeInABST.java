package solutions;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/5/16.
 */
public class InsertNodeInABST {
	/**
	 * @param root: The root of the binary search tree.
	 * @param node: insert this node into the binary search tree
	 * @return: The root of the new binary search tree.
	 */
	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return node;
		}
		if (node != null) {
			TreeNode ptr = root;
			while (ptr.val != node.val) {
				if (ptr.val > node.val) {
					if (ptr.left == null) {
						ptr.left = node;
					}
					ptr = ptr.left;
				} else {
					if (ptr.right == null) {
						ptr.right = node;
					}
					ptr = ptr.right;
				}
			}
		}
		return root;
	}
}
