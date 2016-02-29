package solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Created by feiyi.zhan on 2/10/16.
 */
public class LargestNumber {
	public String largestNumberStream(int[] nums) {
		String largestNumber = IntStream.of(nums)
				.parallel()
				.mapToObj(Integer::toString)
				.sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
				.reduce((t, u) -> t + u).get();
		return largestNumber.charAt(0) == '0' ? "0" : largestNumber;
	}

	/**
	 *@param num: A list of non negative integers
	 *@return: A string
	 */
	public String largestNumber(int[] num) {
		String[] sorted = new String[num.length];
		int length = 0;
		for (int i = 0; i < num.length; ++i) {
			sorted[i] = Integer.toString(num[i]);
			length += sorted[i].length();
		}
		Arrays.sort(sorted, new Comparator<String>() {
			@Override
			public int compare(String lhs, String rhs) {
				return (rhs + lhs).compareTo(lhs + rhs);
			}
		});
		char[] maxNum = new char[length];
		int wptr = 0;
		for (String n : sorted) {
			System.arraycopy(n.toCharArray(), 0, maxNum, wptr, n.length());
			wptr += n.length();
		}
		return maxNum[0] == '0' ? "0" : new String(maxNum);
	}
}
