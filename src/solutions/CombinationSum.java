package solutions;

import java.util.*;

/**
 * Created by PPlovboiledfish on 1/31/16.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates != null && candidates.length > 0 && target > 0) {
            Arrays.sort(candidates);
            Queue<List<Integer>> queue = new LinkedList<>();
            queue.add(new ArrayList<>(Collections.singletonList(0)));
            while (!queue.isEmpty()) {
                List<Integer> l = queue.poll();
                int last = Integer.MAX_VALUE;
                for (int idx = l.size() == 1 ? 0 : l.get(l.size() - 2); idx < candidates.length; ++idx) {
                    if (last == candidates[idx])
                        continue;

                    last = candidates[idx];
                    int sum = l.get(l.size() - 1) + candidates[idx];
                    if (sum <= target - candidates[idx]) {
                        List<Integer> extended = new ArrayList<>(l);
                        extended.set(extended.size() - 1, idx);
                        extended.add(sum);
                        queue.offer(extended);
                    } else if (sum >= target) {
                        if (sum == target) {
                            for (int itL = 0; itL < l.size() - 1; ++itL) {
                                l.set(itL, candidates[l.get(itL)]);
                            }
                            l.set(l.size() - 1, candidates[idx]);
                            res.add(l);
                        }
                        break;
                    }
                }
            }
        }
        return res;
    }

    static public class Test {
        static private CombinationSum _solution = new CombinationSum();

        static public void test(int[] candidates, int target) {
            _solution.combinationSum(candidates, target).forEach(l -> {
                l.stream().map(e -> e + ",").forEach(System.out::print);
                System.out.println();
            });
        }

        static public void randomTest() {
            int[] candidates = {2, 3, 6, 7};
            test(candidates, 7);
        }
    }
}
