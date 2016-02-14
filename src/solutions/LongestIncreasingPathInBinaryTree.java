package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Find longest increasing path in a binary tree.
 * <p>
 * Created by feiyi.zhan on 2/14/16.
 */
public class LongestIncreasingPathInBinaryTree {
	public int longestPath(TreeNode root) {
		return root == null ? 0 : _longestPath(root)[2];
	}

	/**
	 * @param root - root of the given subtree
	 * @return an array of three elements
	 * 0 - longest decreasing path from root
	 * 1 - longest increasing path from root
	 * 2 - longest increasing path in the given subtree
	 */
	private int[] _longestPath(TreeNode root) {
		int[] ret = {1, 1, 1};
		if (root.left != null && root.val != root.left.val) {
			int[] left = _longestPath(root.left);
			_cascadePath(root, ret, root.left, left);
		}
		if (root.right != null && root.val != root.right.val) {
			int[] right = _longestPath(root.right);
			_cascadePath(root, ret, root.right, right);
		}
		return ret;
	}

	private void _cascadePath(TreeNode parent,
	                          int[] parentPaths,
	                          TreeNode child,
	                          int[] childPaths) {
		if (parent.val > child.val) {
			parentPaths[1] = Math.max(parentPaths[1], childPaths[1] + 1);
		} else {
			parentPaths[0] = Math.max(parentPaths[0], childPaths[0] + 1);
		}
		parentPaths[2] = Math.max(childPaths[2], parentPaths[0] + parentPaths[1] - 1);
	}

	static public class Test {
		static private LongestIncreasingPathInBinaryTree _solution = new LongestIncreasingPathInBinaryTree();

		static public void test(TreeNode root) {
			BinaryTreePrinter.printNode(root);
			System.out.println(_solution.longestPath(root));
		}

		static public void randomTest() {
			TreeNode root = new TreeNode(1);
			root.left = new TreeNode(0);
			root.right = new TreeNode(3);
			root.right.left = new TreeNode(4);
			test(root);
		}
	}
}
