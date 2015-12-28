package solutions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by PPlovboiledfish on 12/24/15.
 */
public class NumberOfIslandsII {
    private int accumulator = 0;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        accumulator = 0;
        int[] ids = new int[n * m + 1];
        int[] sz = new int[ids.length];
        Arrays.fill(sz, 1);
        return Stream.of(positions).map(pos -> _update(pos, ids, sz, m, n)).collect(Collectors.toList());
    }

    private int _update(int[] pos, int[] ids, int[] sz, int rowNum, int rowSize) {
        ++accumulator;
        int[][] cells = {{pos[0] - 1, pos[1]}, {pos[0] + 1, pos[1]}, {pos[0], pos[1] - 1}, {pos[0], pos[1] + 1}, pos};
        int encodedPos = _to1D(pos[0], pos[1], rowSize);
        ids[encodedPos] = encodedPos;
        Stream.of(cells)
                .filter(p -> (p[0] >= 0 && p[0] < rowNum && p[1] >= 0 && p[1] < rowSize))
                .mapToInt(cell -> _to1D(cell[0], cell[1], rowSize))
                .filter(p -> ids[p] > 0)
                .reduce((l, r) -> _union(l, r, ids, sz));
        return accumulator;
    }

    public List<Integer> numIslands2ForLeetCode(int m, int n, int[][] positions) {
        accumulator = 0;
        int[] ids = new int[n * m + 1];
        int[] sz = new int[ids.length];
        Arrays.fill(sz, 1);
        List<Integer> ret = new LinkedList<>();
        for (int[] pos : positions) ret.add(_updateForLeetCode(pos, ids, sz, m, n));
        return ret;
    }


    private int _updateForLeetCode(int[] pos, int[] ids, int[] sz, int rowNum, int rowSize) {
        ++accumulator;
        int[][] neighbors = {{pos[0] - 1, pos[1]}, {pos[0] + 1, pos[1]}, {pos[0], pos[1] - 1}, {pos[0], pos[1] + 1}};
        int root = _to1D(pos[0], pos[1], rowSize);
        for (int[] p : neighbors) {
            if (p[0] >= 0 && p[0] < rowNum && p[1] >= 0 && p[1] < rowSize && ids[_to1D(p[0], p[1], rowSize)] > 0)
                root = _union(_to1D(p[0], p[1], rowSize), root, ids, sz);
        }
        ids[root] = root;
        return accumulator;
    }

    private int _union(int l, int r, int[] ids, int[] sz) {
        int rootL = _find(l, ids);
        int rootR = _find(r, ids);
        if (rootL != rootR) {
            --accumulator;
            if (sz[rootL] < sz[rootR]) {
                ids[rootL] = rootR;
                sz[rootR] += sz[rootL];
                return rootR;
            } else {
                ids[rootR] = rootL;
                sz[rootL] += sz[rootR];
                return rootL;
            }
        }
        return rootL;
    }

    private int _find(int pos, int[] ids) {
        if (ids[pos] != 0) {
            while (ids[pos] != pos) pos = ids[pos];
        }
        return pos;
    }

    private int _to1D(int x, int y, int rowSize) {
        return x * rowSize + y + 1;
    }

    static public class Test {
        static private NumberOfIslandsII _solution = new NumberOfIslandsII();

        static public void test(int m, int n, int[][] positions) {
            _solution.numIslands2(m, n, positions).stream().forEach(System.out::println);
//            _solution.numIslands2ForLeetCode(m, n, positions).stream().forEach(System.out::println);
        }

        static public void randomTest() {
//            int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
//            test(3, 3, positions);
            int[][] positions2 = {{0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 2}, {0, 0}, {1, 1}};
            test(3, 3, positions2);
        }
    }
}
