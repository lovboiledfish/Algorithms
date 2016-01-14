package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by PPlovboiledfish on 1/13/16.
 */
public class KthSmallestSumInTwoSortedArrays {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        if (k > A.length * B.length)
            return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(2, (Comparator<Integer>) (o1, o2) -> {
            int i1 = _decodeI(o1, B.length), j1 = _decodeJ(o1, B.length);
            int i2 = _decodeI(o2, B.length), j2 = _decodeJ(o2, B.length);
            return A[i1] + B[j1] - A[i2] - B[j2];
        });
        pq.add(_encode(0, 0, B.length));
        Set<Integer> visited = new HashSet<>();
        visited.add(_encode(0, 0, B.length));
        for (int m = 0; m < k - 1; ++m) {
            int code = pq.poll();
            int i = _decodeI(code, B.length), j = _decodeJ(code, B.length);
            if (i + 1 < A.length) {
                int code1 = _encode(i + 1, j, B.length);
                if (!visited.contains(code1)) {
                    visited.add(code1);
                    pq.offer(code1);
                }
            }
            if (j + 1 < B.length) {
                int code2 = _encode(i, j + 1, B.length);
                if (!visited.contains(code2)) {
                    visited.add(code2);
                    pq.offer(code2);
                }
            }
        }
        int kth = pq.poll();
        return A[_decodeI(kth, B.length)] + B[_decodeJ(kth, B.length)];
    }

    private int _encode(int i, int j, int factor) {
        return i * factor + j;
    }

    private int _decodeI(int code, int factor) {
        return code / factor;
    }

    private int _decodeJ(int code, int factor) {
        return code % factor;
    }

    static public class Test {
        static private KthSmallestSumInTwoSortedArrays _solution = new KthSmallestSumInTwoSortedArrays();

        static public void test(int[] A, int[] B, int k, int ans) {
            Assert.check(ans == _solution.kthSmallestSum(A, B, k));
        }

        static public void randomTest() {
            int[] A = {1, 7, 11};
            int[] B = {2, 4, 6};
            test(A, B, 4, 9);
        }
    }
}
