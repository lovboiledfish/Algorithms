package solutions;


import com.sun.tools.javac.util.Assert;

/**
 * Created by PPlovboiledfish on 12/25/15.
 */
public class SmallestRectangleEnclosingBlackPixels {
    private int _minX, _maxX, _minY, _maxY;

    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0)
            return 0;
        _minX = _minY = Integer.MAX_VALUE;
        _maxX = _maxY = Integer.MIN_VALUE;
        _traverse(image, x, y);
        return (_maxX - _minX + 1) * (_maxY - _minY + 1);
    }

    private void _traverse(char[][] image, int x, int y) {
        if (image[x][y] == '0')
            return;
        image[x][y] = '0';
        if (_minX > x) _minX = x;
        if (_maxX < x) _maxX = x;
        if (_minY > y) _minY = y;
        if (_maxY < y) _maxY = y;
        for (int deltaX = -1; deltaX < 2; ++deltaX)
            if (x + deltaX >= 0 && x + deltaX < image.length)
                for (int deltaY = -1; deltaY < 2; ++deltaY)
                    if (y + deltaY >= 0 && y + deltaY < image[0].length)
                        _traverse(image, x + deltaX, y + deltaY);
    }

    static public class Test {
        static private SmallestRectangleEnclosingBlackPixels _solution = new SmallestRectangleEnclosingBlackPixels();

        static public void test(char[][] image, int x, int y, int ans) {
            Assert.check(ans == _solution.minArea(image, x, y));
        }

        static public void randomTest() {
            char[][] image = {
                    {'0', '0', '1', '0'},
                    {'0', '1', '1', '0'},
                    {'0', '1', '0', '0'}
            };
            test(image, 0, 2, 6);

            char[][] image2 = {{'1'}};
            test(image2, 0, 0, 1);
        }
    }
}
