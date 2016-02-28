package solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by feiyi.zhan on 2/27/16.
 */
public class BinaryRepresentation {
	/**
	 * @param n: Given a decimal number that is passed in as a string
	 * @return: A string
	 */
	public String binaryRepresentation(String n) {
		final String ERROR = "ERROR";
		if (n != null && n.length() > 0) {
			String[] parts = n.split("\\.");
			if (parts.length <= 2) {
				String integer = _integerToBinary(parts[0]);
				if (integer == null) {
					return ERROR;
				}
				String fraction = null;
				if (parts.length == 2) {
					fraction = _fractionToBinary(parts[1]);
					if (fraction == null) {
						return ERROR;
					}
				}
				if (fraction == null || fraction.equals("0")) {
					return integer;
				} else {
					return integer + "." + fraction;
				}
			}
		}
		return ERROR;
	}

	private String _integerToBinary(String integerPart) {
		char[] integer = integerPart.toCharArray();
		for (char c : integer) {
			int digit = c - '0';
			if (digit < 0 || digit > 9) {
				return null;
			}
		}

		StringBuilder bin = new StringBuilder();
		int ptr = 0;
		while (ptr < integer.length) {
			int carrier = 0;
			for (int i = ptr; i < integer.length; ++i) {
				int digit = integer[i] - '0';
				int num = carrier * 10 + digit;
				digit = num >> 1;
				carrier = (num & 1);
				if (digit == 0 && i == ptr) {
					++ptr;
				}
				integer[i] = (char) ('0' + digit);
			}
			bin.append(carrier);
		}
		return bin.reverse().toString();
	}

	private String _fractionToBinary(String fractionPart) {
		char[] fraction = fractionPart.toCharArray();
		boolean isZero = true;
		for (char c : fraction) {
			int digit = c - '0';
			if (digit < 0 || digit > 9) {
				return null;
			}
			if (digit != 0) {
				isZero = false;
			}
		}
		if (isZero) {
			return "0";
		}

		StringBuilder bin = new StringBuilder();
		Set<Integer> visited = new HashSet<Integer>();
		int zeroCount = 0;
		int hash = 0;
		while (!visited.contains(hash) && zeroCount != fraction.length) {
			visited.add(hash);
			hash = 0;
			zeroCount = 0;
			int carrier = 0;
			for (int i = fraction.length - 1; i >= 0; --i) {
				int digit = fraction[i] - '0';
				digit = ((digit << 1) | carrier);
				if (digit > 9) {
					carrier = 1;
					digit -= 10;
				} else {
					carrier = 0;
				}
				fraction[i] = (char) (digit + '0');
				if (digit == 0) {
					++zeroCount;
				}
				hash += ((digit << 7) - digit);
				hash = hash * 5 / 6;
			}
			bin.append(carrier);
		}
		return zeroCount != fraction.length ? null : bin.toString();
	}

	static public class Test {
		static private BinaryRepresentation _solution = new BinaryRepresentation();

		static public void randomTest() {
			String n = "6.1251123434";
			System.out.println(_solution.binaryRepresentation(n));
		}
	}
}
