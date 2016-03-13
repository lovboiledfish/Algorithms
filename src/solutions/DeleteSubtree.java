package solutions;

import java.util.Stack;

/**
 * TS onsite interview question: delete subtree from a tree array.
 * <p>
 * Created by feiyi.zhan on 3/12/16.
 */

class TreeNode {
	public int parent;
	public int val;
	public boolean isValid;
	public boolean visited;

	public TreeNode(int v, int p) {
		parent = p;
		val = v;
		isValid = true;
		visited = false;
	}

	@Override
	public String toString() {
		return isValid ? Integer.toString(val) : "NULL";
	}
}

public class DeleteSubtree {
	static public void deleteSubtree(TreeNode[] treeNodes, int subrootIdx) {
		TreeNode subroot = treeNodes[subrootIdx];
		if (!subroot.isValid) {
			return;
		}
		subroot.isValid = false;
		subroot.visited = true;
		for (TreeNode treeNode : treeNodes) {
			TreeNode ptr = treeNode;
			if (!ptr.visited) {
				Stack<TreeNode> path = new Stack<>();
				while (!ptr.visited) {
					ptr.visited = true;
					path.push(ptr);
					if (ptr.parent >= 0) {
						ptr = treeNodes[ptr.parent];
					} else {
						break;
					}
				}
				if (!ptr.isValid) {
					while (!path.isEmpty()) {
						TreeNode subnode = path.pop();
						subnode.isValid = false;
					}
				}
			}
		}
	}

	static public class Test {
		static public void randomTest() {
			TreeNode[] treeNodes = new TreeNode[]{
					new TreeNode(1, -1), new TreeNode(2, 0), new TreeNode(3, 0),
					new TreeNode(4, 1), new TreeNode(5, 1), new TreeNode(6, 1),
					new TreeNode(7, 2)
			};
			deleteSubtree(treeNodes, 1);
			for (TreeNode treeNode : treeNodes) {
				System.out.println(treeNode);
			}
		}
	}
}
