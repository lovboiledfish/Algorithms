package solutions;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/5/16.
 */
public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
		return _height(root) >= 0;
	}

	private int _height(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = _height(root.left);
		int right = _height(root.right);
		if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
			return -2;
		} else {
			return Math.max(left, right) + 1;
		}
	}
}
