package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 2/29/16.
 */
public class UniqueBinarySearchTreesII {
	public List<TreeNode> generateTrees(int n) {
		List<TreeNode> bts = null;
		if (n >= 1) {
			List<List<TreeNode>> generated = new ArrayList<>(n + 1);
			generated.add(new ArrayList<>(Collections.singletonList(null)));
			generated.add(new ArrayList<>(Collections.singletonList(new TreeNode(1))));
			for (int i = 2; i <= n; ++i) {
				generated.add(new ArrayList<>());
				for (int j = 0; j < i; ++j) {
					for (TreeNode left : generated.get(j)) {
						for (TreeNode right : generated.get(i - 1 - j)) {
							TreeNode root = new TreeNode(j + 1);
							root.left = _clone(left, 0);
							root.right = _clone(right, j + 1);
							generated.get(i).add(root);
						}
					}
				}
			}
			bts = generated.get(n);
		}
		return bts == null ? new ArrayList<>() : bts;
	}

	private TreeNode _clone(TreeNode root, int offset) {
		if (root == null) {
			return null;
		}
		TreeNode cloned = new TreeNode(root.val + offset);
		cloned.left = _clone(root.left, offset);
		cloned.right = _clone(root.right, offset);
		return cloned;
	}
}
