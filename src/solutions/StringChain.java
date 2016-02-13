package solutions;

import com.sun.tools.javac.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Online assessment problem II
 * <p>
 * String Chain
 * <p>
 * Find the longest string chain in a given word dictionary. A string
 * chain is consisted of words among which neighboring ones are only
 * different by one extra character, i.e. "abc" -> "bc" -> "c".
 * <p>
 * Created by PPlovboiledfish on 2/12/16.
 */
public class StringChain {
    public int longestStringChain(String[] dict) {
        if (dict == null || dict.length == 0) {
            return 0;
        }

        Map<String, List<String>> adjList = Arrays.stream(dict).collect(Collectors.toMap(
                word -> word,
                word -> new ArrayList<>()
        ));
        Set<String> children = new HashSet<>();
        adjList.keySet().forEach(word -> {
            char[] substring = Arrays.copyOfRange(word.toCharArray(), 0, word.length() - 1);
            int i = word.length() - 1;
            while (i >= 0) {
                String child = new String(substring);
                if (adjList.containsKey(child)) {
                    children.add(child);
                    adjList.get(word).add(child);
                }
                if (i > 0) {
                    substring[i - 1] = word.charAt(i);
                }
                --i;
            }
        });
        return adjList.keySet().stream()
                .filter(word -> !children.contains(word))
                .parallel()
                .mapToInt(root -> _dfs(adjList, root))
                .max().getAsInt();
    }

    private int _dfs(Map<String, List<String>> adjList, String root) {
        if (adjList.get(root).isEmpty()) {
            return 1;
        }
        int maxDepth = adjList.get(root).stream()
                .parallel()
                .mapToInt(child -> _dfs(adjList, child))
                .max().getAsInt();
        return maxDepth + 1;
    }

    static public class Test {
        static private StringChain _solution = new StringChain();

        static public void test(String[] dict, int ans) {
            Assert.check(_solution.longestStringChain(dict) == ans);
        }

        static public void randomTest() {
            String[] dict = {"a", "b", "ba", "bca", "bda", "bdca"};
            test(dict, 4);
        }
    }
}
