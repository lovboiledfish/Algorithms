package solutions;

import com.sun.tools.javac.util.Assert;
import solutions.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by PPlovboiledfish on 11/12/15.
 */
public class BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root, lastVisit;
        do {
            while (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
            lastVisit = null;
            while (!stack.empty()) {
                ptr = stack.peek();
                if (ptr.right == lastVisit) {
                    stack.pop();
                    res.add(ptr.val);
                    lastVisit = ptr;
                } else {
                    ptr = ptr.right;
                    break;
                }
            }
        } while (!stack.empty());
        return res;
    }


    public List<Integer> postorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode dummy = new TreeNode(-1);
        TreeNode cur, prev = null;

        dummy.left = root;
        cur = dummy;
        while (cur != null) {
            if (cur.left == null) {
                prev = cur;
                cur = cur.right;
            } else {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur)
                    node = node.right;

                if (node.right == null) {
                    node.right = cur;
                    prev = cur;
                    cur = cur.left;
                } else {
                    _reverseVisit(cur.left, prev, res);
                    prev.right = null;
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    private void _reverseVisit(TreeNode from, TreeNode to, List<Integer> res) {
        TreeNode p = to;
        _reverse(from, to);

        while (true) {
            res.add(p.val);
            if (p == from)
                break;
            p = p.right;
        }

        _reverse(to, from);
    }

    private void _reverse(TreeNode from, TreeNode to) {
        if (from == to)
            return;
        TreeNode x = from, y = from.right, z;
        while (x != to) {
            z = y.right;
            y.right = x;
            x = y;
            y = z;
        }
    }

    static public class Test {
        static private BinaryTreePostOrderTraversal _solution = new BinaryTreePostOrderTraversal();

        static public void test(TreeNode root, List<Integer> ans, int mode) {
            List<Integer> res;
            if (mode == 0)
                res = _solution.postorderTraversal(root);
            else
                res = _solution.postorderTraversalMorris(root);
            Assert.check(res.size() == ans.size());
            for (int i = 0; i < res.size(); ++i)
                Assert.check(res.get(i).equals(ans.get(i)));
        }

        static public void randomTest() {
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(2);
            root.right = new TreeNode(8);
            root.left.left = new TreeNode(1);
            root.right.left = new TreeNode(7);
            root.right.right = new TreeNode(9);
            List<Integer> ans = new ArrayList<Integer>() {{
                add(1);
                add(2);
                add(7);
                add(9);
                add(8);
                add(5);
            }};
            test(root, ans, 0);
            test(root, ans, 1);
        }
    }
}
