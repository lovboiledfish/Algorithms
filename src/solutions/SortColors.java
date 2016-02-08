package solutions;

import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 2/7/16.
 */
public class SortColors {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums.length > 0) {
            int wZero = 0;
            int wTwo = nums.length - 1;
            for (int i = 0; wZero <= i && i <= wTwo;) {
                if (nums[i] < 1) {
                    if (_swp(nums, i, wZero)) {
                        ++wZero;
                        continue;
                    }
                } else if (nums[i] > 1) {
                    if (_swp(nums, i, wTwo)) {
                        --wTwo;
                        continue;
                    }
                }
                ++i;
            }
        }
    }

    private boolean _swp(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            return true;
        }
        return false;
    }

    static public class Test {
        static private SortColors _solution = new SortColors();

        static public void test(int[] A) {
            _solution.sortColors(A);
            IntStream.of(A).forEach(System.out::println);
        }

        static public void randomTest() {
            int[] A = {0,2,2,2,2,1,0,1,0,0,0,1,0,2,0};
            test(A);
        }
    }

}
