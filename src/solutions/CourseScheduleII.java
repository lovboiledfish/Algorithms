package solutions;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by PPlovboiledfish on 2/10/16.
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            adjList.add(new ArrayList<>());
        }
        int[] inDeg = new int[numCourses];
        // this handles duplicate relations well...
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[0]).add(prereq[1]);
            ++inDeg[prereq[1]];
        }

        int[] sorted = new int[numCourses];
        int wptr = numCourses - 1;
        for (int i = 0; i < numCourses; ++i) {
            if (inDeg[i] == 0) {
                sorted[wptr--] = i;
            }
        }
        int rptr = numCourses - 1;
        while (wptr >= 0) {
            int root = sorted[rptr--];
            for (int neighbor : adjList.get(root)) {
                --inDeg[neighbor];
                if (inDeg[neighbor] == 0) {
                    sorted[wptr--] = neighbor;
                }
            }
            if (rptr == wptr) {
                return new int[0];
            }
        }
        return sorted;
    }

    static public class Test {
        static private CourseScheduleII _solution = new CourseScheduleII();

        static public void test(int numCourses, int[][] prerequisites) {
            IntStream.of(_solution.findOrder(numCourses, prerequisites)).forEach(System.out::println);
        }

        static public void randomTest() {
            int[][] prerequisites = {
                    {5, 8},
                    {3, 5},
                    {1, 9},
                    {4, 5},
                    {0, 2},
                    {1, 9},
                    {7, 8},
                    {4, 9}
            };
            test(10, prerequisites);
        }
    }
}
