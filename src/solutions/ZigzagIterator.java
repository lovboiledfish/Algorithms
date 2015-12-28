package solutions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by PPlovboiledfish on 12/27/15.
 */
public class ZigzagIterator {
    Iterator<Integer>[] its;
    int which;

    @SuppressWarnings("unchecked")
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        its = new Iterator[2];
        its[0] = v1.iterator();
        its[1] = v2.iterator();
        if (!hasNext())
            which = 1 - which;
    }

    public int next() {
        if (hasNext()) {
            int ans = its[which].next();
            if (its[1 - which].hasNext())
                which = 1 - which;
            return ans;
        } else
            return Integer.MAX_VALUE;
    }

    public boolean hasNext() {
        return its[which].hasNext();
    }
}
