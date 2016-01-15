package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 12/19/15.
 */
public class CountOfSmallerNumbersAfterSelfBIT {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null)
            return null;

        List<Integer> ret = _discretization(nums);
        int[] BIT = new int[nums.length + 1]; // BIT[x] -> number of elements in the range
        IntStream.range(0, ret.size()).forEach(i -> {
            _insert(BIT, ret.get(ret.size() - i - 1));
            ret.set(ret.size() - i - 1, _query(BIT, ret.get(ret.size() - i - 1) - 1));
        });
        return ret;
    }

    private List<Integer> _discretization(int[] nums) {
        ArrayList<Integer> sorted =
                IntStream.of(nums).boxed().sorted().collect(Collectors.toCollection(ArrayList<Integer>::new));
        return IntStream.of(nums).boxed()
                .map(num -> Collections.binarySearch(sorted, num) + 1)
                .collect(Collectors.toCollection(ArrayList<Integer>::new));
    }

    private void _insert(int[] BIT, int val) {
        for (; val < BIT.length; val += (val & (-val)))
            BIT[val] += 1;
    }

    private int _query(int[] BIT, int val) {
        int cnt = 0;
        for (; val > 0; val -= (val & (-val)))
            cnt += BIT[val];
        return cnt;
    }

    static public class Test {
        static private CountOfSmallerNumbersAfterSelfBIT _solution = new CountOfSmallerNumbersAfterSelfBIT();

        static public void test(int[] nums) {
            _solution.countSmaller(nums).stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
