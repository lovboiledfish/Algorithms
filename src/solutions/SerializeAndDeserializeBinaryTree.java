package solutions;

import solutions.utils.BinaryTreePrinter;
import solutions.utils.TreeNode;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class SerializeAndDeserializeBinaryTree {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        _serialize(root, sb);
        return sb.toString();
    }

    private void _serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
        } else {
            sb.append(root.val);
            sb.append(",");
            _serialize(root.left, sb);
            _serialize(root.right, sb);
        }
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        TreeNode dummy = new TreeNode(-1);
        _deserialize(dummy, true, data, 0);
        return dummy.left;
    }

    private int _deserialize(TreeNode root, boolean left, String data, int start) {
        if (start < data.length() && data.charAt(start) != '#') {
            int end = start + 1;
            while (data.charAt(end) != ',') {
                ++end;
            }
            TreeNode node = new TreeNode(Integer.parseInt(data.substring(start, end)));
            if (left) {
                root.left = node;
            } else {
                root.right = node;
            }
            int deserialized = _deserialize(node, true, data, end + 1);
            return _deserialize(node, false, data, deserialized + 1);
        }
        return start + 1;
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
