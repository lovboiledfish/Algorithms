package solutions;

/**
 * Created by feiyi.zhan on 2/21/16.
 */
public class StringToIntegerII {
	/**
	 * @param str: A string
	 * @return An integer
	 */
	public int atoi(String str) {
		int integer = 0;
		if (str != null) {
			int ptr = 0;
			while (ptr < str.length() && str.charAt(ptr) == ' ') {
				++ptr;
			}
			if (ptr < str.length()) {
				int sign = 1;
				if (str.charAt(ptr) == '-') {
					sign = -1;
					++ptr;
				} else if (str.charAt(ptr) == '+') {
					++ptr;
				}
				int end = ptr;
				while (end < str.length() &&
						(str.charAt(end) >= '0' && str.charAt(end) <= '9')) {
					++end;
				}
				final int max = Integer.MAX_VALUE / 10, min = Integer.MIN_VALUE / 10;
				while (ptr < end && integer <= max && integer >= min) {
					int digit = str.charAt(ptr) - '0';
					if (digit < 0 || digit > 9) {
						break;
					}
					if (integer < max && integer > min) {
						integer = integer * 10 + sign * digit;
					} else if (digit <= 7 || (digit == 8 && sign < 0)) {
						integer = integer * 10 + sign * digit;
					} else {
						break;
					}
					++ptr;
				}
				if (ptr < end) {
					return integer > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
				}
			}
		}
		return integer;
	}
}
