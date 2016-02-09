package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    class Holder {
        TreeNode node;
        int end;

        public Holder(TreeNode n, int e) {
            node = n;
            end = e;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        } else {
            return _deserialize(data, 0).node;
        }
    }

    private Holder _deserialize(String data, int start) {
        if (start < data.length()) {
            if (data.charAt(start) == '#') {
                return new Holder(null, start + 1);
            }
            int end = start;
            while (end < data.length() && data.charAt(end) != ',') {
                ++end;
            }
            TreeNode node = new TreeNode(Integer.parseInt(data.substring(start, end)));
            Holder left = _deserialize(data, end + 1);
            Holder right = _deserialize(data, left.end + 1);
            node.left = left.node;
            node.right = right.node;
            return new Holder(node, right.end);
        }
        return new Holder(null, -1);
    }

    static public class Test {
        static private SerializeAndDeserializeBinaryTree _solution = new SerializeAndDeserializeBinaryTree();

        static public void test(TreeNode root) {
            String serialized = _solution.serialize(root);
            System.out.println(serialized);
            TreeNode rebuilt = _solution.deserialize(serialized);
            BinaryTreePrinter.printNode(rebuilt);
        }

        static public void randomTest() {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.right.left = new TreeNode(4);
            root.right.right = new TreeNode(5);
            BinaryTreePrinter.printNode(root);
            test(root);
        }
    }
}
