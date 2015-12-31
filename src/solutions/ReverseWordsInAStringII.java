package solutions;

/**
 * Created by PPlovboiledfish on 12/30/15.
 */
public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        if (s == null || s.length < 2)
            return;
        _reverse(s, 0, s.length);
        int start = 0, end = 0;
        while (end < s.length) {
            if (s[end++] == ' ') {
                _reverse(s, start, end - 1);
                start = end;
            }
        }
        _reverse(s, start, end);
    }

    private void _reverse(char[] s, int start, int end) {
        int mid = (start + end) >> 1;
        for (; start < mid; ++start, --end) {
            char tmp = s[start];
            s[start] = s[end - 1];
            s[end - 1] = tmp;
        }
    }

    static public class Test {
        static private ReverseWordsInAStringII _solution = new ReverseWordsInAStringII();

        static public void test(char[] s) {
            _solution.reverseWords(s);
            System.out.println(new String(s));
        }

        static public void randomTest() {
            test("the PP".toCharArray());
        }
    }
}
