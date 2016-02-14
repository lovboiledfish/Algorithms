package solutions;

import java.util.stream.IntStream;

/**
 * Created by feiyi.zhan on 2/13/16.
 */
public class QuickSelect {
	public int topK(int[] arr, int k) {
		return _quickSelect(arr, 0, arr.length - 1, k);
	}

	private int _quickSelect(int[] arr, int lo, int hi, int k) {
		if (hi < lo || hi - lo + 1 < k) {
			return Integer.MAX_VALUE;
		}
		int pivot = arr[hi];
		int wptr = lo;
		for (int i = lo; i < hi; ++i) {
			if (arr[i] < pivot) {
				_swp(arr, wptr++, i);
			}
		}
		_swp(arr, wptr, hi);
		int pivotRank = wptr - lo + 1;
		if (pivotRank == k) {
			return pivot;
		} else if (pivotRank > k) {
			return _quickSelect(arr, lo, wptr - 1, k);
		} else {
			return _quickSelect(arr, wptr + 1, hi, k - pivotRank);
		}
	}

	private void _swp(int[] arr, int i, int j) {
		if (i != j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}

	static public class Test {
		static private QuickSelect _solution = new QuickSelect();

		static public void randomTest() {
			int[] arr = {99, 1, 3, 5, -1, 102, 50};
			for (int i = 0; i < arr.length; ++i) {
				System.out.println(_solution.topK(arr, i + 1));
			}
			IntStream.of(arr).forEach(System.out::println);
		}
	}
}
