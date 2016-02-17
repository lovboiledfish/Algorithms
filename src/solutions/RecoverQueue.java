package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Recover a queue from the following clue:
 * every elem knows how many larger elem there were before
 * it.
 * <p>
 * Created by feiyi.zhan on 2/16/16.
 */
public class RecoverQueue {
	public int[] recover(List<int[]> clues) {
		Collections.sort(clues, (c1, c2) -> {
			if (c1[0] < c2[0] && c1[1] < c2[1]) {
				return 1;
			} else {
				return 0;
			}
		});
		int[] queue = new int[clues.size()];
		int wptr = 0;
		for (int[] clue : clues) {
			queue[wptr++] = clue[0];
		}
		return queue;
	}

	static public class Test {
		static private RecoverQueue _solution = new RecoverQueue();

		static public void test(int[] queue) {
			IntStream.of(queue).mapToObj(i -> i + ",").forEach(System.out::print);
			System.out.println();
			List<int[]> clues = new ArrayList<>();
			for (int i = 0; i < queue.length; ++i) {
				int[] clue = {queue[i], 0};
				for (int j = 0; j < i; ++j) {
					++clue[1];
				}
				clues.add(clue);
			}
			IntStream.of(_solution.recover(clues)).mapToObj(i -> i + ",").forEach(System.out::print);
		}

		static public void randomTest() {
			int[] queue = {10, 3, 6, 6, 8, 4};
			test(queue);
		}
	}
}
