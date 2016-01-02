package solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by PPlovboiledfish on 1/1/16.
 */
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder serialized = new StringBuilder();
        final String dollar = "$";
        final String caret = "^";
        final String backSlash = "\\";
        final String escapedDollar = "\\$";
        final String escapedCaret = "\\^";
        final String escapedBackSlash = "\\\\";
        strs.stream().sequential()
                .map(s -> "^" + s.replace(backSlash, escapedBackSlash)
                        .replace(dollar, escapedDollar)
                        .replace(caret, escapedCaret) + "$")
                .forEach(serialized::append);
        return serialized.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() < 2) return res;

        final String dollar = "$";
        final String caret = "^";
        final String backSlash = "\\";
        final String escapedDollar = "\\$";
        final String escapedCaret = "\\^";
        final String escapedBackSlash = "\\\\";
        int i = 1, j = 1;
        while (j < s.length()) {
            if (s.charAt(j) == '$' && (j == s.length() - 1 || s.charAt(j + 1) == '^')) {
                if (i < j)
                    res.add(s.substring(i, j).replace(escapedDollar, dollar)
                            .replace(escapedCaret, caret)
                            .replace(escapedBackSlash, backSlash));
                else
                    res.add("");
                j += 2;
                i = j;
            } else
                ++j;
        }
        return res;
    }

    static public class Test {
        static private EncodeAndDecodeStrings _solution = new EncodeAndDecodeStrings();

        static public void test(List<String> strs) {
            String str = _solution.encode(strs);
            System.out.println(str);
            _solution.decode(str).stream().map(s -> "\"" + s + "\"").forEach(System.out::println);
        }

        static public void randomTest() {
            test(Arrays.asList("abc\\$^", "\\", "\\def"));
        }
    }
}
