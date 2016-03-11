package solutions;

/**
 * Created by feiyi.zhan on 3/11/16.
 */
public class GameOfLife {
	static private int[][] _moves = {
			{-1, -1}, {-1, 0}, {-1, 1},
			{0, -1}, {0, 1},
			{1, -1}, {1, 0}, {1, 1}
	};

	// 1 - live, 0 - dead, 2 - live to dead, -1 - dead to live
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[0].length; ++col) {
				int cntLives = 0;
				for (int[] move : _moves) {
					int x = move[0] + row;
					int y = move[1] + col;
					if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] > 0) {
						++cntLives;
					}
				}
				if (board[row][col] > 0) {
					if (cntLives < 2 || cntLives > 3) {
						board[row][col] = 2;
					}
				} else {
					if (cntLives == 3) {
						board[row][col] = -1;
					}
				}
			}
		}
		for (int row = 0; row < board.length; ++row) {
			for (int col = 0; col < board[0].length; ++col) {
				if (board[row][col] == 2) {
					board[row][col] = 0;
				} else if (board[row][col] == -1) {
					board[row][col] = 1;
				}
			}
		}
	}
}
