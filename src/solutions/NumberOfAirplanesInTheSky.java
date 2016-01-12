package solutions;

import solutions.utils.Interval;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PPlovboiledfish on 1/11/16.
 */
public class NumberOfAirplanesInTheSky {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.isEmpty())
            return 0;
        int[] starts = new int[airplanes.size()];
        int[] ends = new int[airplanes.size()];
        int wptr = 0;
        for (Interval interval : airplanes) {
            starts[wptr] = interval.start;
            ends[wptr] = interval.end;
            ++wptr;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int sptr = 0, eptr = 0, cur = 0, max = -1;
        while (eptr < airplanes.size()) {
            if (sptr < airplanes.size() && starts[sptr] < ends[eptr]) {
                ++sptr;
                ++cur;
                max = Math.max(max, cur);
            } else {
                ++eptr;
                --cur;
            }
        }
        return max;
    }
}
