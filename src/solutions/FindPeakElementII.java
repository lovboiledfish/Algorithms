package solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PPlovboiledfish on 1/31/16.
 */
public class FindPeakElementII {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> res = new ArrayList<Integer>();
        if (A != null && A.length > 0) {
            boolean horizontal = true;
            int row = A.length >> 1;
            int col = 0;
            int add = 1;
            while (true){
                if (horizontal) {
                    int maxCol = col;
                    for (int i = col; i >= 0 && i < A[0].length; i += add) {
                        if (A[row][i] > A[row][maxCol]) {
                            maxCol = i;
                        }
                    }
                    col = maxCol;
                    add = (row < A.length - 1 && A[row + 1][col] > A[row][col]) ? 1 : -1;
                    if ((add == 1 && A[row + 1][col] < A[row][col]) ||
                            (add == -1 && A[row - 1][col] < A[row][col])) {
                        break;
                    }
                } else {
                    int maxRow = row;
                    for (int i = row; i >= 0 && i < A.length; i += add) {
                        if (A[i][col] > A[maxRow][col]) {
                            maxRow = i;
                        }
                    }
                    row = maxRow;
                    add = (col < A[0].length - 1 && A[row][col + 1] > A[row][col]) ? 1 : -1;
                    if ((add == 1 && A[row][col + 1] < A[row][col]) ||
                            (add == -1 && A[row][col - 1] < A[row][col])) {
                        break;
                    }
                }
                horizontal = !horizontal;
            }
            res.add(row);
            res.add(col);
        }
        return res;
    }

    static public class Test {
        static private FindPeakElementII _solution = new FindPeakElementII();

        static public void test(int[][] A) {
            _solution.findPeakII(A).forEach(System.out::println);
        }

        static public void randomTest() {
            int[][] A = {
                    {72, 1936, 56, 633, 177, 296, 1615, 10, 191, 84, 24, 624, 379},
                    {195, 1939, 1814, 1601, 660, 1512, 1737, 1060, 1588, 1250, 1361, 1386, 1284},
                    {1248, 1693, 29, 756, 497, 1813, 644, 989, 888, 1679, 663, 1125, 690},
                    {396, 1597, 146, 106, 203, 1760, 20, 1574, 1551, 1285, 1371, 222, 164},
                    {144, 388, 174, 1558, 1429, 1836, 1739, 130, 68, 15, 1844, 1817, 1812},
                    {86, 659, 441, 480, 614, 468, 287, 99, 1184, 1636, 394, 1753, 1704},
                    {1548, 1949, 1870, 100, 1001, 30, 1342, 1030, 1626, 1990, 35, 1848, 1712},
                    {47, 49, 1485, 1454, 1419, 467, 1983, 532, 134, 300, 175, 1161, 1087},
                    {851, 1340, 1271, 1918, 885, 1378, 1517, 1301, 1085, 1719, 714, 1709, 562},
                    {135, 1226, 23, 1640, 34, 26, 1147, 225, 1008, 858, 302, 189, 142}
            };
            test(A);
        }
    }
}
