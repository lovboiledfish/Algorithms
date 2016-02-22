package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feiyi.zhan on 2/21/16.
 */
public class Anagrams {
	/**
	 * @param strs: A list of strings
	 * @return: A list of strings
	 */
	public List<String> anagrams(String[] strs) {
		Map<String, List<String>> groups = new HashMap<String, List<String>>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String hash = new String(chars);
			if (!groups.containsKey(hash)) {
				groups.put(hash, new ArrayList<String>());
			}
			groups.get(hash).add(str);
		}
		List<String> list = new ArrayList<String>();
		for (String group : groups.keySet()) {
			if (groups.get(group).size() > 1) {
				list.addAll(groups.get(group));
			}
		}
		return list;
	}
}
