package solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by PPlovboiledfish on 1/11/16.
 */
public class SurroundedRegions {
    static private int[][] _moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        if (board != null && board.length > 0) {
            char[][] visited = new char[board.length][board[0].length];
            for (int i = 0; i < board.length; ++i) {
                _BFS(board, visited, i, 0);
                _BFS(board, visited, i, board[0].length - 1);
            }
            for (int j = 1; j < board[0].length - 1; ++j) {
                _BFS(board, visited, 0, j);
                _BFS(board, visited, board.length - 1, j);
            }
            for (int i = 1; i < board.length - 1; ++i)
                for (int j = 1; j < board[0].length - 1; ++j)
                    if (visited[i][j] == 0 && board[i][j] == 'O')
                        board[i][j] = 'X';
        }
    }

    private void _BFS(char[][] board, char[][] visited, int i, int j) {
        if (visited[i][j] == 0 && board[i][j] == 'O') {
            Queue<int[]> visiting = new LinkedList<>();
            visiting.offer(new int[]{i, j});
            while (!visiting.isEmpty()) {
                int[] cell = visiting.poll();
                visited[cell[0]][cell[1]] = 1;
                for (int[] move : _moves) {
                    int x = cell[0] + move[0], y = cell[1] + move[1];
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && visited[x][y] == 0 && board[x][y] == 'O')
                        visiting.offer(new int[]{x, y});
                }
            }
        }
    }
}
