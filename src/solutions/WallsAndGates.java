package solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by PPlovboiledfish on 12/27/15.
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0)
            return;
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j)
                if (rooms[i][j] == 0)
                    _BFS(new int[]{i, j}, rooms, new char[rooms.length][rooms[0].length]);

        }
    }

    public void _BFS(int[] start, int[][] rooms,char[][] color) {
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int level = 1, qSize = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < qSize; ++i) {
                int[] cur = queue.poll();
                for (int[] move : moves) {
                    int x = move[0] + cur[0];
                    int y = move[1] + cur[1];
                    if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length
                            && color[x][y] != 1 && rooms[x][y] > 0) {
                        color[x][y] = 1;
                        if (level < rooms[x][y]) {
                            queue.offer(new int[]{x, y});
                            rooms[x][y] = level;
                        }
                    }
                }
            }
            ++level;
            qSize = queue.size();
        }
    }
}
