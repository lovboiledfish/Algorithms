package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by feiyi.zhan on 3/5/16.
 */
public class WordLadderII {
	/**
	 * @param start, a string
	 * @param end, a string
	 * @param dict, a set of string
	 * @return a list of lists of string
	 */
	public List<List<String>> findLaddersReverseIdx(String start, String end, Set<String> dict) {
		List<List<String>> ret = new ArrayList<>();
		List<String> path = new ArrayList<>();
		if (start.equals(end)) {
			path.add(start);
			ret.add(path);
			return ret;
		}

		// build adjacency list with bfs from end to start
		Map<String, List<String>> adjList = new HashMap<>();
		final int depth = _bfs(end, start, new HashSet<>(dict), adjList);
		// search graph for paths from start to end
		_dfs(adjList, path, start, end, ret, depth);
		return ret;
	}

	private int _bfs(String start, String end, Set<String> dict, Map<String, List<String>> adjList) {
		int depth = 0;
		boolean found = false;
		List<Set<String>> levels = new ArrayList<Set<String>>(2) {{
			add(new HashSet<>());
			add(new HashSet<>());
		}};
		levels.get(depth).add(start);
		dict.remove(start);
		while (!levels.get(depth & 1).isEmpty() && !found) {
			++depth;
			for (String word : levels.get((depth - 1) & 1)) {
				char[] meta = word.toCharArray();
				for (int i = 0; i < meta.length; ++i) {
					char origin = meta[i];
					for (char c = 'a'; c <= 'z'; ++c) {
						if (c != origin) {
							meta[i] = c;
							String neighbor = new String(meta);
							if (dict.contains(neighbor)) {
								if (!adjList.containsKey(neighbor)) {
									adjList.put(neighbor, new ArrayList<>());
								}
								adjList.get(neighbor).add(word);
								levels.get(depth & 1).add(neighbor);
								if (neighbor.equals(end)) {
									found = true;
								}
							}
						}
					}
					meta[i] = origin;
				}
			}
			dict.removeAll(levels.get(depth & 1));
			levels.get((depth - 1) & 1).clear();
		}
		return depth;
	}

	private void _dfs(Map<String, List<String>> adjList, List<String> path, String start, String end, List<List<String>> ret, final int depth) {
		if (depth >= 0) {
			path.add(start);
			if (start.equals(end)) {
				ret.add(new ArrayList<>(path));
			} else {
				if (adjList.containsKey(start)) {
					for (String neighbor : adjList.get(start)) {
						_dfs(adjList, path, neighbor, end, ret, depth - 1);
					}
				}
			}
			path.remove(path.size() - 1);
		}
	}

	/**
	 * @param start, a string
	 * @param end, a string
	 * @param dict, a set of string
	 * @return a list of lists of string
	 */
	public List<List<String>> findLaddersTwoSidedBFS(String start, String end, Set<String> dict) {
		// hash set for both ends
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		// initial words in both ends
		set1.add(start);
		set2.add(end);

		// we use a map to help construct the final result
		Map<String, List<String>> map = new HashMap<>();

		// build the map
		helper(dict, set1, set2, map, false);

		List<List<String>> res = new ArrayList<>();
		List<String> sol = new ArrayList<>(Collections.singletonList(start));

		// recursively build the final result
		generateList(start, end, map, sol, res);

		return res;
	}

	boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
		if (set1.isEmpty()) {
			return false;
		}

		if (set1.size() > set2.size()) {
			return helper(dict, set2, set1, map, !flip);
		}

		// remove words on current both ends from the dict
		dict.removeAll(set1);
		dict.removeAll(set2);

		// as we only need the shortest paths
		// we use a boolean value help early termination
		boolean done = false;

		// set for the next level
		Set<String> set = new HashSet<String>();

		// for each string in end 1
		for (String str : set1) {
			for (int i = 0; i < str.length(); i++) {
				char[] chars = str.toCharArray();

				// change one character for every position
				for (char ch = 'a'; ch <= 'z'; ch++) {
					chars[i] = ch;

					String word = new String(chars);

					// make sure we construct the tree in the correct direction
					String key = flip ? word : str;
					String val = flip ? str : word;

					List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();

					if (set2.contains(word)) {
						done = true;

						list.add(val);
						map.put(key, list);
					}

					if (!done && dict.contains(word)) {
						set.add(word);

						list.add(val);
						map.put(key, list);
					}
				}
			}
		}

		// early terminate if done is true
		return done || helper(dict, set2, set, map, !flip);
	}

	void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
		if (start.equals(end)) {
			res.add(new ArrayList<String>(sol));
			return;
		}

		// need this check in case the diff between start and end happens to be one
		// e.g "a", "c", {"a", "b", "c"}
		if (!map.containsKey(start)) {
			return;
		}

		for (String word : map.get(start)) {
			sol.add(word);
			generateList(word, end, map, sol, res);
			sol.remove(sol.size() - 1);
		}
	}

	static public class Test {
		static private WordLadderII _solution = new WordLadderII();

		static public void test(String start, String end, Set<String> dict) {
			_solution.findLaddersReverseIdx(start, end, dict).forEach(list -> {
				list.forEach(str -> System.out.print(str + ", "));
				System.out.println();
			});
		}

		static public void randomTest() {
			String start = "hot";
			String end = "dog";
			Set<String> dict = new HashSet<String>(){{
				addAll(Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
			}};
			test(start, end, dict);
		}
	}
}
