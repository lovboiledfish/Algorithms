package solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by PPlovboiledfish on 11/3/15.
 */
public class Vector2D {
    private Iterator<List<Integer>> _rowIt = null;
    private Iterator<Integer> _colIt = null;

    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d != null && !vec2d.isEmpty()) {
            _rowIt = vec2d.iterator();
            _colIt = _rowIt.next().iterator();
        }
    }

    public int next() {
        if (!hasNext())
            return -1;
        return _colIt.next();
    }

    public boolean hasNext() {
        if (_rowIt == null || _colIt == null)
            return false;

        if (_colIt.hasNext())
            return true;
        while (_rowIt.hasNext()) {
            _colIt = _rowIt.next().iterator();
            if (_colIt.hasNext())
                return true;
        }
        return false;
    }

    static public class Test {
        static public void randomTest() {
            final List<Integer> rowOne = new ArrayList<Integer>() {{
                add(1);
                add(2);
                add(3);
            }};
            final List<Integer> rowTwo = new ArrayList<Integer>() {{
                add(4);
                add(5);
            }};
            final List<Integer> rowThree = new ArrayList<Integer>() {{
                add(6);
                add(7);
                add(8);
                add(8);
            }};
            List<List<Integer>> vec = new ArrayList<List<Integer>>() {{
                add(rowOne);
                add(rowTwo);
                add(new ArrayList<Integer>());
                add(rowThree);
                add(new ArrayList<Integer>());
            }};
            Vector2D it = new Vector2D(vec);
            while (it.hasNext())
                System.out.println(it.next());
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
