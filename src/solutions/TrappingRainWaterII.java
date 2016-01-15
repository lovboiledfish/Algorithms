package solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by PPlovboiledfish on 1/14/16.
 */
public class TrappingRainWaterII {
    class Cell {
        public int x, y, h;

        Cell() {
        }

        Cell(int xx, int yy, int hh) {
            x = xx;
            y = yy;
            h = hh;
        }
    }

    class CellComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell x, Cell y) {
            return x.h - y.h;
        }
    }

    static private int[] dx = {1, -1, 0, 0};
    static private int[] dy = {0, 0, 1, -1};

    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        if (heights.length == 0)
            return 0;
        PriorityQueue<Cell> q = new PriorityQueue<>(1, new CellComparator());
        int n = heights.length;
        int m = heights[0].length;
        char[][] visited = new char[n][m];

        for (int i = 0; i < n; i++) {
            q.offer(new Cell(i, 0, heights[i][0]));
            q.offer(new Cell(i, m - 1, heights[i][m - 1]));
            visited[i][0] = 1;
            visited[i][m - 1] = 1;
        }
        for (int i = 0; i < m; i++) {
            q.offer(new Cell(0, i, heights[0][i]));
            q.offer(new Cell(n - 1, i, heights[n - 1][i]));
            visited[0][i] = 1;
            visited[n - 1][i] = 1;

        }

        int ans = 0;
        while (!q.isEmpty()) {
            Cell now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    q.offer(new Cell(nx, ny, Math.max(now.h, heights[nx][ny])));
                    ans = ans + Math.max(0, now.h - heights[nx][ny]);
                }
            }
        }
        return ans;
    }

    static public class Test {
        static private TrappingRainWaterII _solution = new TrappingRainWaterII();

        static public void randomTest() {
            int[][] heights = {
                    {12, 13, 0, 12},
                    {13, 4, 13, 12},
                    {13, 8, 10, 12},
                    {12, 13, 12, 12},
                    {13, 13, 13, 13}
            };
            System.out.println(_solution.trapRainWater(heights));
        }
    }
}
