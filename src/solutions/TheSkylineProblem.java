package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by feiyi.zhan on 1/4/16.
 */
public class TheSkylineProblem {
	class Height {
		int pos;
		int height;

		Height(int p, int h) {
			pos = p;
			height = h;
		}
	}

	public List<int[]> getSkylineI(int[][] buildings) {
		Height[] starts = new Height[buildings.length];
		Height[] ends = new Height[buildings.length];
		int wptr = 0;
		for (int[] building : buildings) {
			starts[wptr] = new Height(building[0], building[2]);
			ends[wptr] = new Height(building[1], building[2]);
			++wptr;
		}
		Arrays.sort(starts, (x, y) -> (x.pos == y.pos) ? x.height - y.height : x.pos - y.pos);
		Arrays.sort(ends, (x, y) -> x.pos - y.pos);

		TreeMap<Integer, Integer> sortedHeights = new TreeMap<>();
		List<int[]> shape = new ArrayList<>();
		int curHeight = 0;
		int i = 0, j = 0;
		while (i < buildings.length || j < buildings.length) {
			int pos;
			if (i < buildings.length && starts[i].pos <= ends[j].pos) {
				sortedHeights.put(starts[i].height, sortedHeights.containsKey(starts[i].height) ? sortedHeights.get(starts[i].height) + 1 : 1);
				pos = starts[i].pos;
				++i;
			} else {
				if (sortedHeights.get(ends[j].height) == 1) sortedHeights.remove(ends[j].height);
				else sortedHeights.put(ends[j].height, sortedHeights.get(ends[j].height) - 1);
				pos = ends[j].pos;
				++j;
			}
			int maxHeight = (sortedHeights.isEmpty()) ? 0 : sortedHeights.lastKey();
			if (curHeight != maxHeight) {
				curHeight = maxHeight;
				if (!shape.isEmpty() && shape.get(shape.size() - 1)[0] == pos) shape.get(shape.size() - 1)[1] = curHeight;
				else shape.add(new int[]{pos, curHeight});
			}
		}
		return shape;
	}

	public List<int[]> getSkylineII(int[][] buildings) {
		Height[] heights = new Height[buildings.length << 1];
		int wptr = 0;
		for (int[] building : buildings)
			for (int i = 0; i < 2; ++i)
				heights[wptr++] = new Height(building[i], ((i << 1) - 1) * building[2]);
		Arrays.sort(heights, (x, y) -> (x.pos == y.pos) ? x.height - y.height : x.pos - y.pos);

		TreeMap<Integer, Integer> sortedHeights = new TreeMap<>();
		List<int[]> shape = new LinkedList<>();
		int curHeight = 0;
		for (Height height : heights) {
			if (height.height < 0) {
				sortedHeights.put(-height.height, sortedHeights.containsKey(-height.height) ? sortedHeights.get(-height.height) + 1 : 1);
			} else {
				if (sortedHeights.get(height.height) == 1) sortedHeights.remove(height.height);
				else sortedHeights.put(height.height, sortedHeights.get(height.height) - 1);
			}
			int maxHeight = (sortedHeights.isEmpty()) ? 0 : sortedHeights.lastKey();
			if (curHeight != maxHeight) {
				curHeight = maxHeight;
				shape.add(new int[]{height.pos, curHeight});
			}
		}
		return shape;
	}
}
