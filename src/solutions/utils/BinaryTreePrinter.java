package solutions.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PPlovboiledfish on 12/6/15.
 */
public class BinaryTreePrinter {

    public static void printNode(TreeNode root) {
        int maxLevel = BinaryTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BinaryTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BinaryTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BinaryTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (TreeNode node : nodes) {
                BinaryTreePrinter.printWhitespaces(firstSpaces - i);
                if (node == null) {
                    BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (node.left != null)
                    System.out.print("/");
                else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(i + i - 1);

                if (node.right != null)
                    System.out.print("\\");
                else
                    BinaryTreePrinter.printWhitespaces(1);

                BinaryTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(BinaryTreePrinter.maxLevel(node.left), BinaryTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}