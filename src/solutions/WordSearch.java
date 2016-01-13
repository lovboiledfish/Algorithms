package solutions;

/**
 * Created by PPlovboiledfish on 1/12/16.
 */
public class WordSearch {
    static private int[][] _moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * @param board: A list of lists of character
     * @param word:  A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;
        if (word.isEmpty())
            return true;
        char[][] visited = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[0].length; ++j)
                if (_search(board, visited, i, j, word, 0))
                    return true;
        return false;
    }

    private boolean _search(char[][] board, char[][] visited, int i, int j, String word, int ptr) {
        boolean found = false;
        if (board[i][j] == word.charAt(ptr)) {
            visited[i][j] = 1;
            if (ptr == word.length() - 1) {
                found = true;
            } else {
                for (int[] move : _moves) {
                    int x = i + move[0], y = j + move[1];
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length &&
                            visited[x][y] == 0 && _search(board, visited, x, y, word, ptr + 1)) {
                        found = true;
                        break;
                    }
                }
            }
            visited[i][j] = 0;
        }
        return found;
    }
}
