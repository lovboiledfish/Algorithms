package solutions;

import java.util.ArrayList;

/**
 * Created by PPlovboiledfish on 1/30/16.
 */
public class GenerateParentheses {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     */
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        if (n <= 0)
            return res;

        char[] comb = new char[n * 2];
        _dfs(res, comb, 0, 0, n);
        return res;
    }

    private void _dfs(ArrayList<String> res, char[] comb, int wptr, int openCnt, int n) {
        if (wptr == comb.length) {
            res.add(new String(comb));
            return;
        }
        if (n > 0) {
            comb[wptr] = '(';
            _dfs(res, comb, wptr + 1, openCnt + 1, n - 1);
        }
        if (openCnt > 0) {
            comb[wptr] = ')';
            _dfs(res, comb, wptr + 1, openCnt - 1, n);
        }
    }
}
