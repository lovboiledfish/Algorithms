package solutions;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/5/16.
 */
public class ValidateBST {
	/**
	 * @param root: The root of binary tree.
	 * @return: True if the binary tree is BST, or false
	 */
	public boolean isValidBST(TreeNode root) {
		return _validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean _validate(TreeNode root, int lo, int hi) {
		return root == null || !(root.val < lo || root.val > hi) &&
						_validate(root.left, lo, root.val - 1) &&
						_validate(root.right, root.val + 1, hi);
	}
}
