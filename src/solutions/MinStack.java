package solutions;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class MinStack {
    static private int _initialSize = 20000;
    private int[] _minStack = new int[_initialSize];
    private int[] _stack = new int[_initialSize];
    private int _wptr = 1;
    private int _size = _initialSize;

    public void push(int x) {
        if (_wptr == _size) {
            _size = _size * 2;
            int[] enlarged = new int[_size];
            System.arraycopy(_minStack, 0, enlarged, 0, _minStack.length);
            _minStack = enlarged;

            enlarged = new int[_size];
            System.arraycopy(_stack, 0, enlarged, 0, _stack.length);
            _stack = enlarged;
        }

        _stack[_wptr] = _minStack[_wptr] = x;
        ++_wptr;

        _swim(_wptr - 1);
    }

    public void pop() {
        if (_wptr < 2)
            return;

        int idx = _find(_stack[_wptr - 1], 1);

        _swp(idx, _wptr - 1);
        --_wptr;

        _sink(idx);

    }

    public int top() {
        if (_wptr > 1)
            return _stack[_wptr - 1];
        else
            return Integer.MIN_VALUE;
    }

    public int getMin() {
        if (_wptr > 1)
            return _minStack[1];
        else
            return Integer.MIN_VALUE;
    }

    private void _swim(int k) {
        if (k <= 1)
            return;

        if (_less(k, k / 2))
            _swim(k / 2);
    }

    private void _sink(int k) {
        if (k * 2 > _wptr - 1)
            return;

        if (k * 2 + 1 > _wptr - 1) {
            if (_less(k * 2, k))
                _sink(k * 2);
        }
        else {
            if (_minStack[k * 2] > _minStack[k * 2 + 1]) {
                if (_less(k * 2 + 1, k))
                    _sink(k * 2 + 1);
            } else if (_less(k * 2, k))
            _sink(k * 2);
        }
    }

    private boolean _less(int k, int l) {
        if (_minStack[k] < _minStack[l]) {
            _swp(k, l);
            return true;
        }
        return false;
    }

    private void _swp(int k, int l) {
        int tmp = _minStack[k];
        _minStack[k] = _minStack[l];
        _minStack[l] = tmp;
    }

    private int _find(int value, int idx) {
        if (idx > _wptr - 1)
            return -1;
        if (value == _minStack[idx])
            return idx;
        else if (value < _minStack[idx])
            return -1;
        else {
            int found = _find(value, idx * 2);
            if (-1 != found)
                return found;
            return _find(value, idx * 2 + 1);
        }
    }

    static public class Test {
        static public void randomTest() {
            MinStack minStack = new MinStack();
            for (int i = -10000; i <= 5000; ++i)
                minStack.push(i);
            for (int i = 0; i <= 10000; ++i) {
                minStack.getMin();
                minStack.pop();
            }
        }
    }
}
