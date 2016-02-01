package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feiyi.zhan on 2/1/16.
 */
public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> patterns = new ArrayList<>();
		if (n > 0) {
			int[][] board = new int[n][n];
			_dfs(new ArrayList<>(), 0, board, patterns);
		}
		return patterns;
	}

	private void _dfs(List<int[]> queens, int row, int[][] board, List<List<String>> res) {
		int n = board.length;
		if (row == n) {
			char[] rw = new char[n];
			Arrays.fill(rw, '.');
			List<String> ans = new ArrayList<>();
			for (int[] queen : queens) {
				rw[queen[1]] = 'Q';
				ans.add(new String(rw));
				rw[queen[1]] = '.';
			}
			res.add(ans);
		} else {
			for (int i = 0; i < n; ++i) {
				if (board[row][i] == 0) {
					int[] queen = {row, i};
					_placeQueen(board, queen, 1);
					queens.add(queen);
					_dfs(queens, row + 1, board, res);
					queens.remove(queens.size() - 1);
					_placeQueen(board, queen, -1);
				}
			}
		}
	}

	private void _placeQueen(int[][] board, int[] queen, int fill) {
		int n = board.length;

		// left diagonal start point
		int rL = 0, cL = 0;
		int deltaR = queen[0] - queen[1];
		if (deltaR > 0) {
			rL = deltaR;
		} else {
			cL = -deltaR;
		}
		// right diagonal start point
		int rR = n - 1, cR = 0;
		int deltaL = (n - 1 - queen[0]) - queen[1];
		if (deltaL > 0) {
			rR = n - 1 - deltaL;
		} else {
			cR = -deltaL;
		}

		for (int i = 0; i < n; ++i) {
			// row
			board[queen[0]][i] += fill;
			// column
			board[i][queen[1]] += fill;
			// left diagonal
			if (rL + i < n && cL + i < n) {
				board[rL + i][cL + i] += fill;
			}
			// right diagonal
			if (rR - i >= 0 && cR + i < n) {
				board[rR - i][cR + i] += fill;
			}
		}
		board[queen[0]][queen[1]] -= 3 * fill;
	}

	static public class Test {
		static private NQueens _solution = new NQueens();

		static public void test(int n) {
			_solution.solveNQueens(n).forEach(board -> {
				board.forEach(System.out::println);
				System.out.println();
			});
		}

		static public void randomTest() {
			test(4);
		}
	}
}
