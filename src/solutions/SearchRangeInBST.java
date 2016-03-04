package solutions;

import java.util.ArrayList;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/4/16.
 */
public class SearchRangeInBST {
	/**
	 * @param root: The root of the binary search tree.
	 * @param k1 and k2: range k1 to k2.
	 * @return: Return all keys that k1<=key<=k2 in ascending order.
	 */
	public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
		ArrayList<Integer> ret = new ArrayList<>();
		_search(root, k1, k2, ret);
		return ret;
	}

	private void _search(TreeNode root, int k1, int k2, ArrayList<Integer> ret) {
		if (root != null && k2 >= k1) {
			if (k1 < root.val) {
				_search(root.left, k1, Math.min(root.val - 1, k2), ret);
			}
			if (root.val >= k1 && root.val <= k2) {
				ret.add(root.val);
			}
			if (k2 > root.val) {
				_search(root.right, Math.max(root.val + 1, k1), k2, ret);
			}
		}
	}
}
