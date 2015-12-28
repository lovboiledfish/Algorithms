package solutions;

import com.sun.tools.javac.util.Assert;
import solutions.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PPlovboiledfish on 12/25/15.
 */
public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        List<TreeNode> path = new ArrayList<>();
        List<Integer> seq = new ArrayList<>();
        TreeNode ptr = root, last;
        int maxSeq = 0;
        do {
            while (ptr != null) {
                maxSeq = Math.max(maxSeq, _addSeq(ptr, path, seq));
                path.add(ptr);
                ptr = ptr.left;
            }
            last = null;
            while (!path.isEmpty()) {
                ptr = path.get(path.size() - 1);
                if (last == ptr.right) {
                    path.remove(path.size() - 1);
                    seq.remove(seq.size() - 1);
                    last = ptr;
                } else {
                    ptr = ptr.right;
                    break;
                }
            }
        } while (!path.isEmpty());
        return maxSeq;
    }

    private int _addSeq(TreeNode ptr, List<TreeNode> path, List<Integer> seq) {
        int ret = 1;
        if (path.size() > 0) {
            TreeNode parent = path.get(path.size() - 1);
            if (ptr.val - parent.val == 1)
                ret = seq.get(seq.size() - 1) + 1;
        }
        seq.add(ret);
        return ret;
    }

    static public class Test {
        static private BinaryTreeLongestConsecutiveSequence _solution = new BinaryTreeLongestConsecutiveSequence();

        static public void test(TreeNode root, int ans) {
            Assert.check(ans == _solution.longestConsecutive(root));
        }

        static public void randomTest() {
            TreeNode root1 = new TreeNode(1);
            root1.right = new TreeNode(3);
            root1.right.left = new TreeNode(2);
            root1.right.right = new TreeNode(4);
            root1.right.right.right = new TreeNode(5);
            test(root1, 3);

            TreeNode root2 = new TreeNode(2);
            root2.right = new TreeNode(3);
            root2.right.left = new TreeNode(2);
            root2.right.left.left = new TreeNode(1);
            test(root2, 2);

            TreeNode root3 = new TreeNode(1);
            root3.left = new TreeNode(3);
            root3.right = new TreeNode(5);
            root3.left.left = new TreeNode(6);
            root3.left.right = new TreeNode(7);
            root3.right.left = new TreeNode(8);
            root3.right.right = new TreeNode(9);
            test(root3, 1);
        }
    }
}
