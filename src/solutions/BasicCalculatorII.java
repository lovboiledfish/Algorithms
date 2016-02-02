package solutions;

/**
 * Created by PPlovboiledfish on 2/1/16.
 */
public class BasicCalculatorII {
    static private int _prec(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '$':
                return -1;
            default:
                return 10;
        }
    }

    public int calculate(String s) {
        char[] operators = new char[2];
        int oPtr = 0;
        int[] numbers = new int[4];
        int nPtr = 0;

        int number = 0;
        for (int i = 0; i <= s.length(); ++i) {
            char c = i < s.length() ? s.charAt(i) : '$';
            if (c != ' ') {
                if (_prec(c) == 10) {
                    number = number * 10 + (c - '0');
                } else {
                    numbers[nPtr++] = number;
                    number = 0;

                    while (oPtr > 0 && _prec(operators[oPtr - 1]) >= _prec(c)) {
                        char op = operators[--oPtr];
                        int right = numbers[--nPtr];
                        int left = numbers[--nPtr];
                        switch (op) {
                            case '+':
                                numbers[nPtr++] = left + right;
                                break;
                            case '-':
                                numbers[nPtr++] = left - right;
                                break;
                            case '*':
                                numbers[nPtr++] = left * right;
                                break;
                            case '/':
                                numbers[nPtr++] = left / right;
                                break;
                            default:
                        }
                    }
                    operators[oPtr++] = c;
                }
            }
        }
        return numbers[0];
    }
}
