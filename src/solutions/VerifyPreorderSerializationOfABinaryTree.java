package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 2/8/16.
 */
public class VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        return preorder != null && _verify(preorder, 0) == preorder.length();
    }

    private int _verify(String preorder, int start) {
        if (start >= 0 && start < preorder.length()) {
            if (preorder.charAt(start) == '#') {
                return start + 1;
            }
            while(preorder.charAt(start) != ',') {
                ++start;
            }
            int rightSubTree = _verify(preorder, start + 1) + 1;
            return _verify(preorder, rightSubTree);
        }
        return Integer.MIN_VALUE;
    }

    static public class Test {
        static private VerifyPreorderSerializationOfABinaryTree _solution = new VerifyPreorderSerializationOfABinaryTree();

        static public void test(String preorder, boolean ans) {
            Assert.check(ans == _solution.isValidSerialization(preorder));
        }

        static public void randomTest() {
            String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
            test(preorder, true);
        }
    }
}
