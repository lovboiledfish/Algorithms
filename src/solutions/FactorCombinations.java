package solutions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PPlovboiledfish on 12/31/15.
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        int sqrt = (int) Math.sqrt(n);
        for (int factor = 2; factor <= sqrt; ++factor)
            if (n % factor == 0)
                _getFactors(n / factor, factor, new LinkedList<>(Collections.singletonList(factor)), res);
        return res;
    }

    private void _getFactors(int n, int minFactor, List<Integer> prefix, List<List<Integer>> res) {
        List<Integer> tmp = new LinkedList<>(prefix);
        tmp.add(n);
        res.add(tmp);

        int sqrt = (int) Math.sqrt(n);
        for (int factor = minFactor; factor <= sqrt; ++factor) {
            if (n % factor == 0) {
                tmp = new LinkedList<>(prefix);
                tmp.add(factor);
                _getFactors(n / factor, factor, tmp, res);
            }
        }
    }
}
