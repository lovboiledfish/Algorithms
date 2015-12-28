package solutions;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by PPlovboiledfish on 12/23/15.
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        if (word == null)
            return new LinkedList<>();
        if (word.length() < 1)
            return Collections.singletonList(word);

        Queue<String> queue = new LinkedList<>();
        queue.add(word.substring(0, 1));
        queue.add("1");
        IntStream.range(2, word.length() + 1).forEach(
                i -> {
                    String base = word.substring(0, i - 1);
                    char newChar = word.charAt(i - 1);
                    String nextBase = base + newChar;
                    int qSize = queue.size();
                    IntStream.range(0, qSize)
                            .mapToObj(x -> queue.poll())
                            .flatMap(abbr -> Stream.of(abbr + newChar, _countCombo(abbr)))
                            .filter(w -> !w.equals(nextBase))
                            .forEach(queue::add);
                    queue.add(nextBase);
                }
        );
        return (List<String>) queue;
    }

    private String _countCombo(String base) {
        int length = 0;
        do {
            char lastChar = base.charAt(base.length() - length - 1);
            if (lastChar >= '0' && lastChar <= '9')
                ++length;
            else
                break;
        } while (length < base.length());
        if (length > 0) {
            String left = base.substring(0, base.length() - length);
            int num = Integer.parseInt(base.substring(base.length() - length)) + 1;
            return left + Integer.toString(num);
        } else
            return base + "1";
    }

    static public class Test {
        static private GeneralizedAbbreviation _solution = new GeneralizedAbbreviation();

        static public void test(String word) {
            _solution.generateAbbreviations(word).stream().forEach(System.out::println);
        }
    }
}
