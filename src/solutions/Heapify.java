package solutions;

/**
 * Created by PPlovboiledfish on 1/15/16.
 */
public class Heapify {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        if (A == null || A.length < 2)
            return;
        for (int i = A.length >> 1; i >= 0; --i)
            _heapify(A, i);
    }

    private void _heapify(int[] A, int i) {
        int left = (i << 1) + 1;
        int right = left + 1;

        if (left < A.length && A[left] < A[i]) {
            _swap(A, left, i);
            _heapify(A, left);
        }
        if (right < A.length && A[right] < A[i]) {
            _swap(A, right, i);
            _heapify(A, right);
        }
    }

    private void _swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
