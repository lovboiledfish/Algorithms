package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by PPlovboiledfish on 12/20/15.
 */
public class ShortestDistanceFromAllBuildings {
    int[][] gr;
    int[][] dist;
    char[][] color;
    char cnt = 0;

    public int shortestDistance(int[][] grid) {
        gr = grid;
        dist = new int[grid.length][grid[0].length];
        color = new char[grid.length][grid[0].length];

        IntStream.range(0, grid.length)
                .sequential()
                .forEach(x -> IntStream
                        .range(0, grid[0].length)
                        .sequential()
                        .filter(y -> grid[x][y] == 1)
                        .forEach(y -> _BFS(new int[]{x, y})));

        OptionalInt min = IntStream.range(0, grid.length)
                .parallel()
                .flatMap(i -> IntStream.range(0, grid[0].length)
                        .parallel()
                        .filter(j -> (dist[i][j] > 0 && color[i][j] == cnt))
                        .map(j -> dist[i][j]))
                .min();
        return (min.isPresent()) ? min.getAsInt() : -1;
    }

    private void _BFS(int[] origin) {
        ++cnt;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(origin);
        int level = 1;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            final int l = level;
            IntStream.range(0, qSize).mapToObj(i -> queue.poll())
                    .flatMap(pos -> Stream.of(new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}})
                            .parallel()
                            .map(mov -> new int[]{pos[0] + mov[0], pos[1] + mov[1]})
                            .filter(p -> (p[0] >= 0 && p[0] < gr.length && p[1] >= 0 && p[1] < gr[0].length &&
                                    color[p[0]][p[1]] == cnt - 1 && gr[p[0]][p[1]] == 0))
                    )
                    .forEach(pos -> {
                        color[pos[0]][pos[1]] = cnt;
                        dist[pos[0]][pos[1]] += l;
                        queue.add(new int[]{pos[0], pos[1]});
                    });
            ++level;
        }
    }

    static public class Test {

        static public void test(int[][] grid, int ans) {
            ShortestDistanceFromAllBuildings _solution = new ShortestDistanceFromAllBuildings();
            Assert.check(ans == _solution.shortestDistance(grid));
        }

        static public void randomTest() {
            int[][] grid = {
                    {1, 0, 2, 0, 1},
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0}
            };
            test(grid, 7);

            int[][] grid2 = {{1}};
            test(grid2, -1);

            int[][] grid3 = {
                    {0, 2, 1},
                    {1, 0, 2},
                    {0, 1, 0}
            };
            test(grid3, -1);

            int[][] grid4 = {
                    {1, 1},
                    {0, 1}
            };
            test(grid4, -1);
        }
    }
}
