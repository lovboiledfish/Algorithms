package solutions;

/**
 * Created by PPlovboiledfish on 12/9/15.
 *
 * Question: Range Sum Query 2D - Immutable
 *
 */
public class NumMatrix {
    class SegTreeNode {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        int sum = 0;
    }

    private SegTreeNode[] _treeX;
    private SegTreeNode[][] _treeY;
    private int[][] _matrix = null;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return;

        _matrix = matrix;

        _treeX = new SegTreeNode[_matrix.length << 1 + 2];
        for (int i = 0; i < _treeX.length; ++i)
            _treeX[i] = new SegTreeNode();

        _treeY = new SegTreeNode[_matrix.length << 1 + 2][_matrix[0].length << 1 + 2];
        for (int i = 0; i < _treeY.length; ++i) {
            for (int j = 0; j < _treeY[0].length; ++j)
                _treeY[i][j] = new SegTreeNode();
        }

        _build(1, 1, 1, matrix.length - 1, matrix[0].length - 1);
    }

    public void update(int row, int col, int val) {
        if (_matrix != null)
            _add(1, val - _matrix[row][col], row, col);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (_matrix == null)
            return 0;
        return _query(1, row1 + 1, col1 + 1, row2 + 1, col2 + 1);
    }

    private void _build(int root, int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2)
            return;

        SegTreeNode node = _treeX[root];
        node.x1 = x1;
        node.y1 = y1;
        node.x2 = x2;
        node.y2 = y2;
        if (x1 != x2) {
            int mid = (x1 + x2) >> 1;
            _build(root << 1, x1, y1, mid, y2);
            _build(root << 1 | 1, mid + 1, y1, x2, y2);
        }
        _buildY(root, 1, x1, y1, x2, y2);
        node.sum = _treeY[root][1].sum;
    }

    private void _buildY(int rootX, int rootY, int x1, int y1, int x2, int y2) {
        if (y1 > y2)
            return;

        SegTreeNode node = _treeY[rootX][rootY];
        node.x1 = x1;
        node.y1 = y1;
        node.x2 = x2;
        node.y2 = y2;
        if (y1 == y2) {
            if (x1 == x2) {
                node.sum = _matrix[x1 - 1][y1 - 1];
            } else {
                int midX = (x1 + x2) >> 1;
                node.sum = _query(1, x1, y1, midX, y2) + _query(1, midX + 1, y1, x2, y2);
            }
            return;
        }
        int midY = (y1 + y2) >> 1;
        _buildY(rootX, rootY << 1, x1, y1, x2, midY);
        _buildY(rootX, rootY << 1 | 1, x1, midY + 1, x2, y2);
        node.sum = _treeY[rootX][rootY << 1].sum + _treeY[rootX][rootY << 1 | 1].sum;
    }

    private int _query(int root, int x1, int y1, int x2, int y2) {
        SegTreeNode node = _treeX[root];
        if ((x1 <= node.x1) && (node.x2 <= x2))
            return _queryY(root, 1, x1, y1, x2, y2);
        if (node.x1 == node.x2)
            return 0;

        int mid = (node.x1 + node.x2) >> 1;
        int ret = 0;
        if (x1 <= mid)
            ret = _query(root << 1, x1, y1, mid, y2);
        if (mid <= x2)
            ret += _query(root << 1 | 1, mid + 1, y1, x2, y2);
        return ret;
    }

    private int _queryY(int rootX, int rootY, int x1, int y1, int x2, int y2) {
        SegTreeNode node = _treeY[rootX][rootY];
        if ((y1 <= node.y1) && (node.y2 <= y2))
            return node.sum;
        if (node.y1 == node.y2)
            return 0;

        int mid = (node.y1 + node.y2) >> 1;
        int ret = 0;
        if (y1 <= mid)
            ret = _queryY(rootX, rootY << 1, x1, y1, x2, y2);
        if (mid <= y2)
            ret += _queryY(rootX, rootY << 1 | 1, x1, y1, x2, y2);
        return ret;
    }

    private void _add(int root, int adder, int x, int y) {
        SegTreeNode node = _treeX[root];
        if ((node.x1 <= x) && (x <= node.x2)) {
            node.sum += adder;
            _addY(root, 1, adder, x, y);

            if (node.x1 != node.x2) {
                int mid = (node.x1 + node.x2) >> 1;
                if (x <= mid)
                    _add(root << 1, adder, x, y);
                else
                    _add(root << 1 | 1, adder, x, y);
            }
        }
    }

    private void _addY(int rootX, int rootY, int adder, int x, int y) {
        SegTreeNode node = _treeY[rootX][rootY];
        if ((node.y1 <= y) && (y <= node.y2)) {
            node.sum += adder;

            if (node.y1 != node.y2) {
                int mid = (node.y1 + node.y2) >> 1;
                if (y <= mid)
                    _addY(rootX, rootY << 1, adder, x, y);
                else
                    _addY(rootX, rootY << 1 | 1, adder, x, y);
            }
        }
    }

    static public class Test {
        static public void randomTest() {
            int[][] arr = {
                    {3, 6, 0, 7, 8, 1},
                    {0, 9, 2, 7, 0, 5},
                    {2, 1, 2, 4, 8, 9},
                    {8, 7, 6, 3, 3, 2},
                    {6, 1, 9, 9, 0, 7},
                    {7, 6, 8, 4, 5, 1},
            };

            NumMatrix segTree = new NumMatrix(arr);

            System.out.println(segTree.sumRegion(0, 0, 2, 2));
            System.out.println(segTree.sumRegion(3, 3, 5, 5));
            System.out.println(segTree.sumRegion(0, 3, 2, 5));
            System.out.println(segTree.sumRegion(3, 0, 5, 2));
            System.out.println(segTree.sumRegion(0, 0, 5, 5));

            segTree.update(3, 4, 6);
            System.out.println(segTree.sumRegion(0, 0, 2, 2));
            System.out.println(segTree.sumRegion(3, 3, 5, 5));
            System.out.println(segTree.sumRegion(0, 3, 2, 5));
            System.out.println(segTree.sumRegion(3, 0, 5, 2));
            System.out.println(segTree.sumRegion(0, 0, 5, 5));

            int[][] arr2 = {
                    {3, 0, 1, 4, 2},
                    {5, 6, 3, 2, 1},
                    {1, 2, 0, 1, 5},
                    {4, 1, 0, 1, 7},
                    {1, 0, 3, 0, 5},
            };
            segTree = new NumMatrix(arr2);
            segTree.sumRegion(2, 1, 4, 3);
            segTree.update(3, 2, 2);
            segTree.sumRegion(2, 1, 4, 3);

            int[][] arr3 = {{0, -5, 9, 1, -8, 5, 8, 1, 1, 5}};
            segTree = new NumMatrix(arr3);
            segTree.sumRegion(0, 5, 0, 9);
            segTree.update(0, 3, -1);
            segTree.sumRegion(0, 3, 0, 6);

            int[][] arr4 = {
                    {2, 1, 8, -3, -6, 1, -3, 5, 3, 8, -6, 0, -6},
                    {0, -2, -9, 0, 2, 3, -7, -2, -1, 2, -4, -1, 4},
                    {3, 2, 7, 0, 1, 8, -1, 0, 6, -2, -7, -3, 3},
                    {2, 9, -8, 5, 4, 4, 1, -6, 4, -3, 4, -7, 0},
                    {1, 0, 1, 5, 2, 4, -4, -6, -1, 8, 6, 5, 5},
                    {-6, -2, -1, -2, -4, -2, 9, -5, -9, -5, -1, 5, 6},
                    {-8, 6, -3, -4, 9, -1, -3, -6, 8, 3, -5, 9, 7},
                    {-1, -7, 4, -3, 7, 3, -7, 4, -4, 1, -6, 5, -6},
                    {7, 8, 6, -7, 2, 2, -9, 0, 9, -7, -6, -4, -5},
                    {4, 8, 3, 9, 2, 9, 5, 1, -5, 7, 6, 1, 8},
                    {5, 9, 3, 9, 3, 8, 2, 4, -8, -1, -8, 2, 3},
                    {-9, -6, 6, 4, 4, -1, 8, 2, 3, -4, 0, 2, -4},
                    {4, -8, -6, 3, -2, 3, 4, 5, 3, -6, 2, -8, -7},
                    {-8, 4, 1, 4, 4, 5, 0, -5, 4, 6, -1, 0, 3},
                    {3, -2, 8, 4, 1, -5, 1, -6, -8, 8, 2, 4, 1},
                    {-1, 8, -5, -9, -5, 8, -9, 2, 7, 2, -3, -5, -9},
                    {8, -5, -6, -2, 4, 0, -3, -5, 2, 2, -3, 4, 5},
                    {0, 9, 6, -3, -9, 0, -7, -3, -9, -5, -6, 9, -2}
            };
            NumMatrix sol = new NumMatrix(arr4);
            sol.update(13,9,-6);
            sol.sumRegion(9,0,12,5);
            sol.sumRegion(11,3,14,12);
            sol.update(0,2,9);
        }
    }
}
