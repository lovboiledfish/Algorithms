package solutions;

import java.util.stream.IntStream;

/**
 * Created by feiyi.zhan on 2/10/16.
 */
public class LargestNumber {
	public String largestNumber(int[] nums) {
		String largestNumber = IntStream.of(nums)
				.parallel()
				.mapToObj(Integer::toString).sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
				.reduce((t, u) -> t + u).get();
		return largestNumber.charAt(0) == '0' ? "0" : largestNumber;
	}
}
