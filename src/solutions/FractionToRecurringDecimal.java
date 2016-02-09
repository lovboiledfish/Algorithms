package solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feiyi.zhan on 2/9/16.
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int n, int d) {
		if (n == 0) {
			return "0";
		}

		long numerator = n, denominator = d;
		boolean positive = true;
		if (numerator < 0L) {
			numerator = -numerator;
			positive = false;
		}
		if (denominator < 0L) {
			denominator = -denominator;
			positive = !positive;
		}

		long intPart = 0;
		if (numerator >= denominator) {
			intPart = numerator / denominator;
			numerator = numerator % denominator;
		}
		if (numerator == 0) {
			return (positive ? "" : "-") + Long.toString(intPart);
		}

		StringBuilder fractionalPart = new StringBuilder();
		Map<Long, Integer> remainsHash = new HashMap<>();
		remainsHash.put(numerator, fractionalPart.length() + 1);

		numerator *= 10;
		while (numerator < denominator) {
			fractionalPart.append('0');
			remainsHash.put(numerator, fractionalPart.length() + 1);
			numerator *= 10;
		}

		int repeating = -1;
		while (numerator > 0) {
			fractionalPart.append((char) ((numerator / denominator) + '0'));
			numerator = numerator % denominator;
			if (remainsHash.containsKey(numerator)) {
				repeating = remainsHash.get(numerator);
				break;
			} else {
				remainsHash.put(numerator, fractionalPart.length() + 1);
			}
			numerator *= 10;
		}

		if (repeating < 0) {
			return (positive ? "" : "-") + intPart +
					(fractionalPart.length() > 0 ? "." + fractionalPart : "");
		} else {
			return (positive ? "" : "-") + intPart + "." +
					fractionalPart.substring(0, repeating - 1) +
					"(" + fractionalPart.substring(repeating - 1) + ")";
		}
	}

	static public class Test {
		static private FractionToRecurringDecimal _solution = new FractionToRecurringDecimal();

		static public void test(int numerator, int denominator) {
			System.out.println(_solution.fractionToDecimal(numerator, denominator));
		}

		static public void randomTest() {
			test(0, -5);
			test(-1, Integer.MIN_VALUE);
			test(Integer.MIN_VALUE, 1);
			test(1, 90);
			test(0, 3);
			test(673, 9900);
			test(1, 200);
			test(1, 3);
			test(1, 6);
			test(1, 7);
//			test(1, Integer.MAX_VALUE);
		}
	}
}
