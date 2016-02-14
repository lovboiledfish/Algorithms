package solutions;

import java.util.NoSuchElementException;
import java.util.Stack;

import solutions.utils.TreeNode;

/**
 * Created by feiyi.zhan on 2/14/16.
 */
public class BSTIterator {
	private Stack<TreeNode> _trace = new Stack<>();

	public BSTIterator(TreeNode root) {
		TreeNode ptr = root;
		while (ptr != null) {
			_trace.push(ptr);
			ptr = ptr.left;
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !_trace.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		if (hasNext()) {
			TreeNode top = _trace.pop();
			TreeNode ptr = top.right;
			while (ptr != null) {
				_trace.push(ptr);
				ptr = ptr.left;
			}
			return top.val;
		}
		throw new NoSuchElementException();
	}
}
