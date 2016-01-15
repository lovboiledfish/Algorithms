package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 12/19/15.
 */
public class CountOfSmallerNumbersAfterSelfBST {
    class BSTNode {
        int leftCnt, cnt, val;
        BSTNode left, right;
    }

    public List<Integer> countSmaller(int[] nums) {
        BSTNode bst = new BSTNode();
        Integer[] ret = new Integer[nums.length];
        IntStream.range(0, nums.length)
                .map(i -> nums.length - i - 1)
                .forEach(i -> ret[i] = _insert(bst, nums[i]));
        return Arrays.asList(ret);
    }

    private int _insert(BSTNode root, int val) {
        BSTNode cur = root, parent = null;
        int cnt = 0;
        while (true) {
            if (cur == null) {
                cur = new BSTNode();
                cur.val = val;
                if (val < parent.val) parent.left = cur;
                else parent.right = cur;
            }
            if (cur.val == val) {
                ++cur.cnt;
                return cnt + cur.leftCnt;
            }
            parent = cur;
            if (cur.val < val) {
                cnt += cur.cnt + cur.leftCnt;
                cur = cur.right;
            }
            else {
                ++cur.leftCnt;
                cur = cur.left;
            }
        }
    }

    static public class Test {
        static private CountOfSmallerNumbersAfterSelfBST _solution = new CountOfSmallerNumbersAfterSelfBST();

        static public void test(int[] nums) {
            _solution.countSmaller(nums).stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
