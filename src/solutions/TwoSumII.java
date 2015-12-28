package solutions;

/**
 * Created by PPlovboiledfish on 12/18/15.
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers != null && numbers.length > 1) {
            int[] res = new int[2];
            res[0] = 1;
            res[1] = numbers.length;
            while (res[0] < res[1]) {
                if (numbers[res[0]-1] + numbers[res[1]-1] == target)
                    return res;

                if (numbers[res[0]-1] + numbers[res[1]-1] < target)
                    ++res[0];
                else
                    --res[1];
            }
        }
        return new int[2];
    }
}
