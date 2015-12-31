package solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

import com.sun.tools.javac.util.Assert;

/**
 * Created by feiyi.zhan on 12/30/15.
 */
public class AlienDictionary {
	private class Graph extends HashMap<Character, Set<Character>> {
		public void add(char c) {
			if (!containsKey(c))
				put(c, new HashSet<>());
		}

		public boolean addEdge(char r, char l) {
			add(r);
			add(l);
			if (isConnected(l, r))
				return false;
			get(r).add(l);
			return true;
		}

		public boolean isConnected(char r, char l) {
			return r == l || get(r).stream().anyMatch(successor -> isConnected(successor, l));
		}

		public char[] topologicalSort() {
			if (isEmpty())
				return null;

			Map<Character, Integer> inDegrees = new HashMap<>();
			keySet().stream().forEach(c -> {
				if (!inDegrees.containsKey(c)) inDegrees.put(c, 0);
				get(c).forEach(successor -> {
					if (inDegrees.containsKey(successor)) inDegrees.put(successor, inDegrees.get(successor) + 1);
					else inDegrees.put(successor, 1);
				});
			});
			Queue<Character> queue = new LinkedList<>();
			char[] res = new char[keySet().size()];
			int w = 0;
			do {
				inDegrees.keySet().stream().filter(c -> inDegrees.get(c) == 0).forEach(queue::add);
				int qSize = queue.size();
				final int wptr = w;
				IntStream.range(0, qSize).forEach(i -> {
					char c = queue.poll();
					res[wptr + i] = c;
					inDegrees.remove(c);
					get(c).stream().filter(inDegrees::containsKey)
							.forEach(successor -> inDegrees.put(successor, inDegrees.get(successor) - 1));
				});
				w += qSize;
			} while (!inDegrees.isEmpty());
			return res;
		}
	}

	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) return null;
		if (words.length == 1) return words[0];
		Graph graph = new Graph();
		if (IntStream.range(1, words.length).allMatch(i -> {
			String left, right;
			left = words[i - 1];
			right = words[i];
			int k = 0;
			boolean added = false;
			for (; k < Math.max(left.length(), right.length()); ++k) {
				char r = 0, l = 0;
				if (k < right.length()) {
					r = right.charAt(k);
					graph.add(r);
				}
				if (k < left.length()) {
					l = left.charAt(k);
					graph.add(l);
				}
				if (!added && k < Math.min(right.length(), left.length()) && l != r) {
					if (!graph.addEdge(l, r)) return false;
					else added = true;
				}
			}
			return true;
		})) {
			char[] res = graph.topologicalSort();
			return (res == null) ? "" : new String(res);
		} else
			return "";
	}

	static public class Test {
		static private AlienDictionary _solution = new AlienDictionary();

		static public void test(String[] words, String ans) {
			String res = _solution.alienOrder(words);
			Assert.check(ans.equals(res));
		}

		static public void randomTest() {
//			test(new String[]{"wrt", "wrf", "er", "ett", "rftt"}, "wertf");
			test(new String[]{"a", "b", "a"}, "");
//			test(new String[]{"z", "z"}, "z");
//			test(new String[]{"ab","adc"}, "");
			test(new String[]{"za","zb","ca","cb"}, "azbc");
		}
	}
}
