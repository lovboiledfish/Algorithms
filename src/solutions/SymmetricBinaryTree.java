package solutions;import java.util.Stack;

/**
 * Created by feiyi.zhan on 2/19/16.
 */
public class SymmetricBinaryTree {
	static class TreeNode {
		int val;
		TreeNode left, right;

		public TreeNode(int v) {
			val = v;
			left = null;
			right = null;
		}
	}

	static public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		Stack<TreeNode> leftPath = new Stack<TreeNode>();
		if (root.left != null) {
			leftPath.push(root.left);
		}
		Stack<TreeNode> rightPath = new Stack<TreeNode>();
		if (root.right != null) {
			rightPath.push(root.right);
		}
		while (!leftPath.isEmpty() && !rightPath.isEmpty()) {
			TreeNode leftNode = leftPath.pop();
			TreeNode rightNode = rightPath.pop();
			if (leftNode.val != rightNode.val ||
					(leftNode.left == null && rightNode.right != null) ||
					(leftNode.left != null && rightNode.right == null) ||
					(leftNode.right == null && rightNode.left != null) ||
					(leftNode.right != null && rightNode.left == null) ) {
				return false;
			}
			if (leftNode.right != null) {
				leftPath.push(leftNode.right);
				rightPath.push(rightNode.left);
			}
			if (leftNode.left != null) {
				leftPath.push(leftNode.left);
				rightPath.push(rightNode.right);
			}
		}
		return leftPath.isEmpty() && rightPath.isEmpty();
	}

	static public boolean test(TreeNode root, boolean ans) {
		return ans == isSymmetric(root);
	}
}
