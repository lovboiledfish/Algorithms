package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feiyi.zhan on 2/1/16.
 */
public class NQueens {
	// sets to optimize validation process
	private char[] _cols, _sums, _diffs;
	// result
	private List<List<String>> _patterns;

	public List<List<String>> solveNQueens(int n) {
		_patterns = new ArrayList<>();
		if (n > 0) {
			_cols = new char[n];
			_sums = new char[n << 1];
			_diffs = new char[n << 1];
			_dfs(n, new ArrayList<>(), 0);
		}
		return _patterns;
	}

	private void _dfs(int n, List<int[]> queens, int row) {
		if (row == n) {
			char[] rw = new char[n];
			Arrays.fill(rw, '.');
			List<String> ans = new ArrayList<>();
			for (int[] queen : queens) {
				rw[queen[1]] = 'Q';
				ans.add(new String(rw));
				rw[queen[1]] = '.';
			}
			_patterns.add(ans);
		} else {
			for (int i = 0; i < n; ++i) {
				int sum = row + i;
				int diff = row - i + n;
				if (_cols[i] == 0 && _sums[sum] == 0 && _diffs[diff] == 0) {
					int[] queen = {row, i};
					queens.add(queen);
					_setQueen(i, sum, diff, (char)1);
					_dfs(n, queens, row + 1);
					queens.remove(queens.size() - 1);
					_setQueen(i, sum, diff, (char)0);
				}
			}
		}
	}

	private void  _setQueen(int col, int sum, int diff, char val) {
		_cols[col] = val;
		_sums[sum] = val;
		_diffs[diff] = val;
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
