package solutions;

import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 2/7/16.
 */
public class InterleavingPositiveAndNegativeNumbers {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        if (A.length > 1) {
            int even = 0;
            int odd = 1;
            while (true) {
                while (even < A.length && A[even] > 0) {
                    even += 2;
                }
                while (odd < A.length && A[odd] < 0) {
                    odd += 2;
                }
                if (odd >= A.length || even >= A.length) {
                    break;
                }
                _swp(A, odd, even);
            }
            int ptr = Math.min(even, odd);
            if (ptr < A.length) {
                int wptr = ptr;
                boolean positive = A[ptr] < 0;
                for (; ptr < A.length; ptr +=2) {
                    if (positive == A[ptr] > 0) {
                        _swp(A, ptr, wptr);
                        wptr += 2;
                    }
                }
                if (wptr < A.length) {
                    for (int i = 0; i < A.length - 1; i += 2) {
                        _swp(A, i, i + 1);
                    }
                }
            }
        }
    }

    private void _swp(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    static public class Test {
        static private InterleavingPositiveAndNegativeNumbers _solution = new InterleavingPositiveAndNegativeNumbers();

        static public void test(int[] A) {
            _solution.rerange(A);
            IntStream.of(A).forEach(System.out::println);
        }

        static public void randomTest() {
            int[] A = {-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18};
            test(A);
        }
    }
}
