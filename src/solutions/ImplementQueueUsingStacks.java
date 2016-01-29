package solutions;

import java.util.Stack;

/**
 * Created by PPlovboiledfish on 1/28/16.
 */
public class ImplementQueueUsingStacks {
    private Stack<Integer> _stack1 = new Stack<>();
    private Stack<Integer> _stack2 = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        _stack1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        _transfer();
        _stack2.pop();
    }

    // Get the front element.
    public int peek() {
        _transfer();
        return _stack2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return _stack1.isEmpty() && _stack2.isEmpty();
    }

    private void _transfer() {
        if (_stack2.isEmpty()) {
            while (!_stack1.isEmpty()) {
                _stack2.push(_stack1.pop());
            }
        }
    }
}
