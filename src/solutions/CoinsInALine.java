package solutions;

/**
 * Created by PPlovboiledfish on 1/20/16.
 */
public class CoinsInALine {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        return n % 3 != 0;
    }
}
