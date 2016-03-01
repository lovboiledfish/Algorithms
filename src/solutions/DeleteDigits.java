package solutions;

import java.util.Arrays;

/**
 * Created by feiyi.zhan on 2/29/16.
 */
public class DeleteDigits {
	/**
	 *@param A: A positive integer which has N digits, A is a string.
	 *@param k: Remove k digits.
	 *@return: A string
	 */
	public String deleteDigits(String A, int k) {
		if (k >= A.length()) {
			return "0";
		}

		char[] digits = A.toCharArray();
		int start = 0;
		int i = start;
		for (; start < k; ++start) {
			while (i < digits.length - 1 && digits[i] <= digits[i + 1]) {
				++i;
			}
			if (i > start) {
				_remove(digits, i, start);
			} else {
				i = start + 1;
			}
		}
		while (start < digits.length && digits[start] == '0') {
			++start;
		}
		return start >= digits.length? "0" : new String(Arrays.copyOfRange(digits, start, digits.length));
	}

	private void _remove(char[] arr, int pos, int start) {
		while (pos > start) {
			char tmp = arr[pos];
			arr[pos] = arr[pos - 1];
			arr[pos - 1] = tmp;
			--pos;
		}
	}
}
