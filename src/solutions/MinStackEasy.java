package solutions;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/29/16.
 */
public class MinStackEasy {
    private Stack<Integer> _elems;
    private Stack<Integer> _mins;

    public MinStackEasy() {
        _elems = new Stack<>();
        _mins = new Stack<>();
    }

    public void push(int number) {
        _elems.push(number);
        if (_mins.isEmpty() || min() >= number) {
            _mins.push(number);
        }
    }

    public int pop() {
        if (_elems.peek().equals(_mins.peek())) {
            _mins.pop();
        }
        return _elems.pop();
    }

    public int min() {
        return _mins.peek();
    }
}
