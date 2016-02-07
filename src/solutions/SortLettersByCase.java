package solutions;

/**
 * Created by PPlovboiledfish on 2/7/16.
 */
public class SortLettersByCase {
    /**
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        if (chars != null && chars.length > 0) {
            int wptr = chars.length - 1;
            for (int i = chars.length - 1; i >= 0; --i) {
                if (chars[i] <= 'Z') {
                    if (wptr != i) {
                        char tmp = chars[wptr];
                        chars[wptr] = chars[i];
                        chars[i] = tmp;
                    }
                    --wptr;
                }
            }
        }
    }
}
