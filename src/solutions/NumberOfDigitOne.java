package solutions;

/**
 * Created by PPlovboiledfish on 2/11/16.
 */
public class NumberOfDigitOne {
    static public int[] _scales = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    static public int[] _numOnes = new int[10];
    static {
        _numOnes[1] = 1;
        for (int i = 2; i < 10; ++i) {
            _numOnes[i] = _numOnes[i - 1] * 10 + _scales[i - 1];
        }
    }

    public int countDigitOne(int n) {
        int count = 0;
        for (int i = 9; i >= 0; --i) {
            if (n >= _scales[i]) {
                int digit = n / _scales[i];
                n %= _scales[i];
                if (digit == 1) {
                    count += _numOnes[i] + n + 1;
                } else {
                    count += digit * _numOnes[i] + _scales[i];
                }
            }
        }
        return count;
    }
}
