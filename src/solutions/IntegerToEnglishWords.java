package solutions;

/**
 * Created by feiyi.zhan on 2/10/16.
 */
public class IntegerToEnglishWords {
	static private String _zero = "Zero";
	static private String[] _ones = {"" , " One" , " Two" , " Three" , " Four" , " Five" , " Six" , " Seven" , " Eight" , " Nine" };
	static private String[] _teens = {" Ten" , " Eleven" , " Twelve" , " Thirteen" , " Fourteen" , " Fifteen" , " Sixteen" , " Seventeen" , " Eighteen", " Nineteen" };
	static private String[] _tens = {null, null, " Twenty" , " Thirty" , " Forty" , " Fifty" , " Sixty" , " Seventy" , " Eighty" , " Ninety" };
	static private String _hundred = " Hundred" ;
	static private String[] _scales = {"", " Thousand" , " Million" , " Billion" };
	static private int[] _scaleThresholds = {1, 1000 , 1000000 , 1000000000};

	public String numberToWords(int num) {
		if (num == 0) {
			return _zero;
		}
		StringBuilder words = new StringBuilder();
		for (int i = _scales.length - 1; i >= 0; --i) {
			if (num >= _scaleThresholds[i]) {
				words.append(_numberUnderThousandToWords(num / _scaleThresholds[i])).append(_scales[i]);
				num %= _scaleThresholds[i];
			}
		}
		return words.toString().trim();
	}

	private String _numberUnderThousandToWords(int num) {
		StringBuilder words = new StringBuilder();
		if (num >= 100) {
			words.append(_ones[num / 100]).append(_hundred);
			num %= 100;
		}
		if (num >= 20) {
			words.append(_tens[num / 10]).append(_ones[num % 10]);
		} else if (num >= 10) {
			words.append(_teens[num - 10]);
		} else {
			// TODO: is "and" needed?
			words.append(_ones[num]);
		}
		return words.toString();
	}
}
