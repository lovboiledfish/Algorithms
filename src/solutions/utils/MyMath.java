package solutions.utils;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class MyMath {
    static public int min(int... args) {
        if (args == null || args.length == 0)
            return 0;

        int min = Integer.MAX_VALUE;
        for (int arg : args) min = Math.min(min, arg);

        return min;
    }

    static public int max(int... args) {
        if (args == null || args.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        for (int arg : args) max = Math.max(max, arg);

        return max;
    }
}
