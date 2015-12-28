package solutions;

import java.util.TreeMap;

/**
 * Created by PPlovboiledfish on 12/18/15.
 */
public class TwoSumIII {
    private TreeMap<Integer, Integer> _hash = new TreeMap<>();

    // Add the number to an internal data structure.
    public void add(int number) {
        if (_hash.containsKey(number))
            _hash.put(number, _hash.get(number) + 1);
        else
            _hash.put(number, 1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        if (_hash.isEmpty()) return false;
        int l = _hash.firstKey(), r = _hash.lastKey();
        while (l < r) {
            int sum = l + r;
            if (sum == value)
                return true;
            if (sum > value) {
                if ((l << 1) > value)
                    return false;
                r = _hash.floorKey(value - l);
            } else {
                if ((r << 1) < value)
                    return false;
                l = _hash.ceilingKey(value - r);
            }
        }
        return (l == r) && _hash.get(l) > 1 && (l << 1) == value;
    }
}
