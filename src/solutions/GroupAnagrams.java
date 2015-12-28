package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0)
            return ret;

        HashMap<String, List<String>> hash = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] letters = s.toCharArray();
            Arrays.sort(letters);
            String str = new String(letters);
            if (hash.containsKey(str))
                hash.get(str).add(s);
            else {
                List<String> newList = new ArrayList<String>();
                newList.add(s);
                Arrays.sort(letters);
                hash.put(str, newList);
                ret.add(newList);
            }
        }
        for (List<String> list : ret)
            Collections.sort(list);
        return ret;
    }

    static public class Test {
        static private GroupAnagrams _solution = new GroupAnagrams();

        static public void test(String[] s) {
            List<List<String>> ret = _solution.groupAnagrams(s);
            for (List<String> list : ret) {
                for (String str : list)
                    System.out.print(str+", ");
                System.out.println();
            }
        }

        static public void randomTest() {
            String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
            test(s);
        }
    }
}
