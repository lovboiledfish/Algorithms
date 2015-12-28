package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 11/1/15.
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> _map = new HashMap<String, List<String>>();
        List<List<String>> ret = new ArrayList<List<String>>();

        for (String s : strings) {
            boolean found = false;
            for (String p : _map.keySet()) {
                if (_sameGroup(p, s)) {
                    _map.get(p).add(s);
                    found = true;
                    break;
                }
            }
            if (!found) {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(s);
                _map.put(s, newList);
                ret.add(newList);
            }
        }
        for (List<String> list : ret)
            Collections.sort(list);
        return ret;
    }

    private boolean _sameGroup(String p, String s) {
        if (p.length() != s.length())
            return false;
        if (p.isEmpty())
            return true;

        int dist = (p.charAt(0) - s.charAt(0));
        for (int i = 1; i < p.length(); ++i) {
            if ((dist - (p.charAt(i) - s.charAt(i))) % 26 != 0)
                return false;
        }
        return true;
    }

    static public class Test {
        static private GroupShiftedStrings _solution = new GroupShiftedStrings();

        static public void test(String[] strings) {
            List<List<String>> ret = _solution.groupStrings(strings);
            for (List<String> list : ret) {
                for (String s : list)
                    System.out.print(s+", ");
                System.out.println();
            }
        }

        static public void randomTest() {
            String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
            test(strings);
        }
    }
}
