package solutions;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by PPlovboiledfish on 12/25/15.
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A[0].length != B.length)
            return null;

        char[] rowsA = new char[A.length];
        char[] colsB = new char[B[0].length];
        int[][] res = new int[A.length][B[0].length];

        IntStream.range(0, A.length)
                .parallel()
                .filter(i -> Arrays.stream(A[i]).parallel().anyMatch(cell -> cell != 0))
                .forEach(i -> rowsA[i] = 1);
        IntStream.range(0, B[0].length)
                .parallel()
                .filter(j -> IntStream.range(0, B.length).parallel().anyMatch(i -> B[i][j] != 0))
                .forEach(j -> colsB[j] = 1);
        IntStream.range(0, A.length)
                .parallel()
                .filter(i -> rowsA[i] != 0)
                .forEach(i -> IntStream.range(0, B[0].length)
                        .parallel()
                        .filter(j -> colsB[j] != 0)
                        .forEach(j -> res[i][j] = IntStream.range(0, A[0].length)
                                .parallel()
                                .map(k -> A[i][k] * B[k][j]).sum())
                );

        return res;
    }

    public int[][] multiplyForLeetCode(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A[0].length != B.length)
            return null;

        char[] rowsA = new char[A.length];
        char[] colsB = new char[B[0].length];
        int[][] res = new int[A.length][B[0].length];

        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j)
                if (A[i][j] != 0) {
                    rowsA[i] = 1;
                    break;
                }
        }
        for (int j = 0; j < B[0].length; ++j)
            for (int[] aB : B)
                if (aB[j] != 0) {
                    colsB[j] = 1;
                    break;
                }
        for (int i = 0; i < A.length; ++i) {
            if (rowsA[i] != 0)
                for (int j = 0; j < B[0].length; ++j)
                    if (colsB[j] != 0) {
                        for (int k = 0; k < A[0].length; ++k)
                            res[i][j] += A[i][k] * B[k][j];
                    }
        }
        return res;
    }

    static public class Test {
        static private SparseMatrixMultiplication _solution = new SparseMatrixMultiplication();

        static public void test(int[][] A, int[][] B) {
            int[][] res1 = _solution.multiply(A, B);
            Stream.of(res1).forEach(row -> {
                Arrays.stream(row).forEach(System.out::print);
                System.out.println();
            });
            System.out.println("=====================");
            int[][] res2 = _solution.multiplyForLeetCode(A, B);
            Stream.of(res2).forEach(row -> {
                Arrays.stream(row).forEach(System.out::print);
                System.out.println();
            });
        }

        static public void randomTest() {
            int[][] A = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
            int[][] B = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
            test(A, B);
        }
    }
}
