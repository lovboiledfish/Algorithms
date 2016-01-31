package solutions;

/**
 * Created by PPlovboiledfish on 1/30/16.
 */
public class MutiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0)
            return null;
        if (num1.length() > num2.length()) {
            String tNum = num1;
            num1 = num2;
            num2 = tNum;
        }
        int s = num1.length(), l = num2.length();
        if ("0".equals(num1))
            return num1;

        int[] product = new int[s + l - 1];
        for (int i = 0; i < product.length; ++i) {
            for (int a = Math.max(0, i - l + 1); a <= Math.min(i, s - 1); ++a) {
                product[i] += (num1.charAt(a) - '0') * (num2.charAt(i - a) - '0');
            }
        }

        char[] buf = new char[product.length];
        int carrier = 0;
        for (int i = product.length - 1; i >= 0; --i) {
            int d = product[i] + carrier;
            buf[i] = (char) ('0' + (d % 10));
            carrier = d / 10;
        }

        String ans = new String(buf);
        if (carrier > 0)
            ans = ((char) ('0' + carrier)) + ans;
        return ans;
    }
}
