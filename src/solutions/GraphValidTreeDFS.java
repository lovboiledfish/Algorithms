package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 12/27/15.
 *
 * This is too slow for its time complexity is exponential
 *
 */
public class GraphValidTreeDFS {
    private int zeroCnt, oneCnt, cnt;
    int[] inDegrees;

    public boolean validTree(int n, int[][] edges) {
        if (n <= 0)
            return false;

        inDegrees = new int[n];
        zeroCnt = n;
        oneCnt = 0;
        cnt = n;

        for (int[] edge : edges)
            _add(edge[1]);
        return _DFS(0, edges);
    }

    private boolean _DFS(int wptr, int[][] edges) {
        if (wptr == edges.length)
            return _isValid();

        if (!_DFS(wptr + 1, edges)) {
            int[] edge = edges[wptr];
            _remove(edge[1]);
            _add(edge[0]);
            boolean ans = _DFS(wptr + 1, edges);
            _remove(edge[0]);
            _add(edge[1]);
            return ans;
        }
        return true;
    }

    private void _add(int toNode) {
        switch (inDegrees[toNode]) {
            case 0:
                --zeroCnt;
                ++oneCnt;
                break;
            case 1:
                --oneCnt;
                break;
            default:
        }
        ++inDegrees[toNode];
    }

    private void _remove(int toNode) {
        switch (inDegrees[toNode]) {
            case 1:
                --oneCnt;
                ++zeroCnt;
                break;
            case 2:
                ++oneCnt;
                break;
            default:
        }
        --inDegrees[toNode];
    }

    private boolean _isValid() {
        return zeroCnt == 1 && oneCnt == cnt - 1;
    }
}
