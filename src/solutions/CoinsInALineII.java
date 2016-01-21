package solutions;

/**
 * Created by PPlovboiledfish on 1/21/16.
 */
public class CoinsInALineII {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null)
            return true;
        int all = 0, first = 0;
        for (int i = 0; i < values.length; ++i) {
            all += values[i];
            if (i % 3 != 2)
                first += values[i];
        }
        return first > all - first;
    }
}
