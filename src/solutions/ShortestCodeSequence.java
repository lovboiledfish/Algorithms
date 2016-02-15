package solutions;

import java.util.Arrays;

import com.sun.tools.javac.util.Assert;

/**
 * Generate the shortest character sequence that contains
 * all codes in a given space. For example, for a code space
 * of one digit, the shortest char sequence can be "0123456789".
 * <p>
 * Created by feiyi.zhan on 2/14/16.
 */
public class ShortestCodeSequence {
	/**
	 * Generating the code sequence as a hamiltonian ring.
	 *
	 * @param k - digits in the code space
	 * @return code sequence string
	 */
	public String getSequenceForSpace(int k) {
		if (k <= 0) {
			return "";
		}
		final int scale = (int) Math.pow(10, k);
		char[] visited = new char[scale];
		int num = scale - 1;
		visited[num] = 1;
		int left = num;
		StringBuilder seq = new StringBuilder(Integer.toString(num));
		while (left > 0) {
			int digit = 0;
			int tmp = (num * 10) % scale;
			while (digit < 10) {
				if (visited[tmp + digit] == 0) {
					num = tmp + digit;
					seq.append(digit);
					visited[num] = 1;
					--left;
					break;
				}
				++digit;
			}
			if (digit == 10) {
				throw new IllegalStateException();
			}
		}
		return seq.toString();
	}

	public static class Test {
		static private ShortestCodeSequence _solution = new ShortestCodeSequence();

		static public void randomTest() {
			int k = 2;
			String seq = _solution.getSequenceForSpace(k);
			char[] num = new char[k];
			Arrays.fill(num, '0');
			for (int i = 0; i < Math.pow(10, k); ++i) {
				int digit = k - 1;
				int carrier = 1;
				while (digit >= 0 && carrier > 0) {
					int tmp = num[digit] - '0' + carrier;
					carrier = tmp / 10;
					tmp = tmp % 10;
					num[digit] = (char) ('0' + tmp);
					--digit;
				}
				Assert.check(seq.contains(new String(num)));
			}
		}
	}
}
