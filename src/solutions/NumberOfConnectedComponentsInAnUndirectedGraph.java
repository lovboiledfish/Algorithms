package solutions;

/**
 * Created by feiyi.zhan on 12/29/15.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
	public int countComponents(int n, int[][] edges) {
		if (n < 1) return 0;
		int[] ids = new int[n];
		for (int i = 1; i < n; ++i)
			ids[i] = i;
		int cnt = n;
		for (int[] edge : edges) {
			int root1 = _find(ids, edge[0]);
			int root2 = _find(ids, edge[1]);
			if (root1 != root2) {
				--cnt;
				ids[root2] = root1;
			}
		}
		return cnt;
	}

	private int _find(int[] ids, int n) {
		while (ids[n] != n)
			n = ids[n];
		return n;
	}
}
