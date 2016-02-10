package solutions;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 2/9/16.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        return nums.length > IntStream.of(nums).boxed().collect(Collectors.toSet()).size();
    }
}
