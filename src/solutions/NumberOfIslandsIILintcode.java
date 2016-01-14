package solutions;

import solutions.utils.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by PPlovboiledfish on 1/13/16.
 */
public class NumberOfIslandsIILintcode {
    static private int[][] _moves = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if (operators == null || operators.length == 0)
            return res;

        int[] parent = new int[n * m];
        for (int i = 1; i < parent.length; ++i)
            parent[i] = i;
        Set<Integer> islands = new HashSet<>();
        for (Point p : operators) {
            int id = _find(parent, p.x * m + p.y);
            if (!islands.contains(id)) {
                islands.add(id);
                for (int[] move : _moves) {
                    int i = p.x + move[0], j = p.y + move[1];
                    if (i >= 0 && i < n && j >= 0 && j < m) {
                        int idd = _find(parent, i * m + j);
                        if (islands.contains(idd)) _union(parent, idd, id, islands);
                    }
                }
            }
            res.add(islands.size());
        }
        return res;
    }

    private int _find(int[] parent, int id) {
        while (parent[id] != id)
            id = parent[id];
        return id;
    }

    private void _union(int[] parent, int id1, int id2, Set<Integer> islands) {
        id1 = _find(parent, id1);
        id2 = _find(parent, id2);
        if (id1 != id2) {
            int merged = Math.max(id1, id2);
            islands.remove(merged);
            parent[merged] = Math.min(id1, id2);
        }
    }
}
