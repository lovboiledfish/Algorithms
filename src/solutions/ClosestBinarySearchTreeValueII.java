package solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 12/28/15.
 */
public class ClosestBinarySearchTreeValueII {
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		Stack<TreeNode> smaller = new Stack<>();
		Stack<TreeNode> larger = new Stack<>();

		TreeNode ptr = root;
		while (ptr != null) {
			if (ptr.val == target) {
				if (smaller.isEmpty()) smaller.push(ptr);
				else larger.push(ptr);
				break;
			}
			if (ptr.val < target) {
				smaller.push(ptr);
				ptr = ptr.right;
			} else {
				larger.push(ptr);
				ptr = ptr.left;
			}
		}
		List<Integer> leftVals = _findKValues(smaller, k, true);
		List<Integer> rightVals = _findKValues(larger, k, false);
		return _merge(leftVals, rightVals, target, k);
	}

	private List<Integer> _findKValues(Stack<TreeNode> roots, int k, boolean less) {
		List<Integer> res = new LinkedList<>();
		while (res.size() < k && !roots.isEmpty()) {
			TreeNode cur = roots.pop();
			res.add(cur.val);
			_inOrder(less? cur.left : cur.right, k, res, less);
		}
		return res;
	}

	private void _inOrder(TreeNode root, int k, List<Integer> res, boolean reversed) {
		if (root != null) {
			_inOrder(reversed? root.right : root.left, k, res, reversed);
			if (res.size() < k) {
				res.add(root.val);
				_inOrder(reversed? root.left : root.right, k, res, reversed);
			}
		}
	}

	private List<Integer> _merge(List<Integer> l, List<Integer> r, double target, int k) {
		if (l.size() + r.size() <= k) {
			l.addAll(r);
			return l;
		}
		List<Integer> tmp = new LinkedList<>();
		while (tmp.size() < k) {
			if (l.isEmpty())
				tmp.add(r.remove(0));
			else if (r.isEmpty())
				tmp.add(l.remove(0));
			else if (Math.abs(l.get(0) - target) > Math.abs(r.get(0) - target))
				tmp.add(r.remove(0));
			else
				tmp.add(l.remove(0));
		}
		return tmp;
	}

	static public class Test {
		static public ClosestBinarySearchTreeValueII _solution = new ClosestBinarySearchTreeValueII();

		static public void test(TreeNode root, double target, int k) {
			BinaryTreePrinter.printNode(root);
			_solution.closestKValues(root, target, k).stream().forEach(System.out::println);
		}

		static public void randomTest() {
			TreeNode root = new TreeNode(10);
			root.left = new TreeNode(5);
			root.right = new TreeNode(15);
			root.left.left = new TreeNode(3);
			root.left.right = new TreeNode(7);
			root.right.left = new TreeNode(12);
			root.right.right = new TreeNode(17);
			root.left.left.left = new TreeNode(1);
			root.left.left.right = new TreeNode(4);
			root.left.right.left = new TreeNode(6);
			root.left.right.right = new TreeNode(8);
			root.right.left.left = new TreeNode(11);
			root.right.left.right = new TreeNode(13);
			root.right.right.left = new TreeNode(16);
			root.right.right.right = new TreeNode(18);
			root.left.right.right.right = new TreeNode(9);

			test(root, 17, 6);
		}
	}
}
