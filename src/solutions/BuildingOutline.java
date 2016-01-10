package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by PPlovboiledfish on 1/9/16.
 */
public class BuildingOutline {
    private static class Height {
        int pos;
        int height;

        Height(int p, int h) {
            pos = p;
            height = h;
        }
    }

    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> outline = new ArrayList<>();
        if (buildings == null || buildings.length == 0)
            return outline;

        Height[] starts = new Height[buildings.length];
        Height[] ends = new Height[buildings.length];
        int wptr = 0;
        for (int[] building : buildings) {
            starts[wptr] = new Height(building[0], building[2]);
            ends[wptr] = new Height(building[1], building[2]);
            ++wptr;
        }
        Arrays.sort(starts, new Comparator<Height>() {
            @Override
            public int compare(Height o1, Height o2) {
                return (o1.pos == o2.pos) ? o1.height - o2.height : o1.pos - o2.pos;
            }
        });
        Arrays.sort(ends, new Comparator<Height>() {
            @Override
            public int compare(Height o1, Height o2) {
                return o1.pos - o2.pos;
            }
        });

        TreeMap<Integer, Integer> heightHeap = new TreeMap<>();
        int startPtr = 0, endPtr = 0, outlineStart = -1, curPos, curHeight = -1;
        while (endPtr < buildings.length) {
            if (startPtr < buildings.length && starts[startPtr].pos <= ends[endPtr].pos) {
                Integer heightCnt = heightHeap.get(starts[startPtr].height);
                heightHeap.put(starts[startPtr].height, (heightCnt == null) ? 1 : heightCnt + 1);
                curPos = starts[startPtr].pos;
                ++startPtr;
            } else {
                int heightCnt = heightHeap.get(ends[endPtr].height);
                if (heightCnt == 1) heightHeap.remove(ends[endPtr].height);
                else heightHeap.put(ends[endPtr].height, heightCnt - 1);
                curPos = ends[endPtr].pos;
                ++endPtr;
            }
            if (heightHeap.isEmpty() || heightHeap.lastKey() != curHeight) {
                if (outlineStart > 0 && curHeight > 0 && outlineStart < curPos)
                    outline.add(new ArrayList<>(Arrays.asList(outlineStart, curPos, curHeight)));
                outlineStart = curPos;
                curHeight = (heightHeap.isEmpty()) ? -1 : heightHeap.lastKey();
            }
        }
        return outline;
    }

    static public class Test {
        static private BuildingOutline _solution = new BuildingOutline();

        static public void test(int[][] buildings) {
            _solution.buildingOutline(buildings).forEach(building -> {
                building.stream().map(num -> num + ",").forEach(System.out::print);
                System.out.println();
            });
        }

        static public void randomTest() {
            int[][] buildings = {
                    {1, 3, 3},
                    {2, 4, 4},
                    {5, 6, 1}
            };
            test(buildings);
        }
    }
}
