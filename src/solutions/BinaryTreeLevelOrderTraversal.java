package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 3/5/16.
 */
public class BinaryTreeLevelOrderTraversal {
	/**
	 * @param root: The root of binary tree.
	 * @return: Level order a list of lists of integer
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
		if (root != null) {
			Queue<TreeNode> levels = new LinkedList<>(Collections.singletonList(root));
			nodes.add(new ArrayList<>(Collections.singletonList(root.val)));
			while (true) {
				final int size = levels.size();
				ArrayList<Integer> nextLevel = new ArrayList<>();
				for (int i = 0; i < size; ++i) {
					TreeNode node = levels.poll();
					if (node.left != null) {
						levels.offer(node.left);
						nextLevel.add(node.left.val);
					}
					if (node.right != null) {
						levels.offer(node.right);
						nextLevel.add(node.right.val);
					}
				}
				if (!nextLevel.isEmpty()) {
					nodes.add(nextLevel);
				} else {
					break;
				}
			}
		}
		return nodes;
	}
}
