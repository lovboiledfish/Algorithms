package solutions;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by PPlovboiledfish on 1/16/16.
 */
public class FlattenIterator implements Iterator {

    private final Stack<Iterator> iteratorStack;

    private Object next;

    public FlattenIterator(List list) {
        if (list == null) {
            throw new NullPointerException();
        }

        iteratorStack = new Stack<>();
        iteratorStack.push(list.iterator());
    }

    public void remove() {
        /* Not implemented */
    }


    private void moveToNext() {
        if ((next == null) && !iteratorStack.empty()) {
            if (iteratorStack.peek().hasNext()) {
                final Object next = iteratorStack.peek().next();
                if (next instanceof Iterator) {
                    iteratorStack.push((Iterator) next);
                    moveToNext();
                } else if (next instanceof Iterable) {
                    iteratorStack.push(((Iterable) next).iterator());
                    moveToNext();
                } else if (next instanceof Array) {
                    iteratorStack.push(Arrays.asList((Array) next).iterator());
                    moveToNext();
                } else {
                    this.next = next;
                }
            } else {
                iteratorStack.pop();
                moveToNext();
            }
        }
    }

    public boolean hasNext() {
        moveToNext();
        return next != null;
    }

    public Object next() {
        moveToNext();
        if (next == null) {
            throw new NoSuchElementException();
        } else {
            Object objectToReturn = next;
            next = null;
            return objectToReturn;
        }
    }
}
