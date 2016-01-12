package solutions;

import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 1/11/16.
 */
public class NumberOfIslands {
    static private int[][] _moves = {{-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        char[][] visited = new char[grid.length][grid[0].length];
        int[] ids = new int[grid.length * grid[0].length];
        for (int i = 0; i < ids.length; ++i)
            ids[i] = i;
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1' && visited[i][j] == 0)
                    count = _update(ids, i, j, grid, visited, ++count);
            }
        }
        return count;
    }

    private int _update(int[] ids, int i, int j, char[][] grid, char[][] visited, int count) {
        visited[i][j] = 1;
        for (int[] move : _moves) {
            int x = i + move[0], y = j + move[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1' && visited[x][y] != 0) {
                if (_union(ids, i * grid[0].length + j, x * grid[0].length + y)) --count;
            }
        }
        return count;
    }

    private int _find(int[] ids, int elem) {
        while (ids[elem] != elem)
            elem = ids[elem];
        return elem;
    }

    private boolean _union(int[] ids, int elem1, int elem2) {
        elem1 = _find(ids, elem1);
        elem2 = _find(ids, elem2);
        if (elem1 == elem2) return false;

        if (elem1 < elem2) ids[elem2] = elem1;
        else ids[elem1] = elem2;
        return true;
    }

    static public class Test {
        static private NumberOfIslands _solution = new NumberOfIslands();

        static public void test(char[][] grid, int ans) {
            Assert.check(ans == _solution.numIslands(grid));
        }

        static public void randomTest() {
//            char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0',},{'1','1','0','0','0',},{'0','0','0','0','0',}};
//            test(grid, 1);

            char[][] grid1 = {{'1'}, {'1'}};
            test(grid1, 1);
        }
    }
}
