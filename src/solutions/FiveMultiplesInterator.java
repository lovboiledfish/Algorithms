package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * Question from TS
 *
 * Created by feiyi.zhan on 3/11/16.
 */
public class FiveMultiplesInterator implements Iterator<Integer> {
	private Iterator<Integer> _source;
	private Integer _next = null;

	public FiveMultiplesInterator(Iterator<Integer> random) {
		_source = random;
	}

	@Override
	public boolean hasNext() {
		if (_next == null) {
			while (_source.hasNext() && _next == null) {
				Integer candidate = _source.next();
				if (candidate % 5 == 0) {
					_next = candidate;
				}
			}
		}
		return _next != null;
	}

	@Override
	public Integer next() {
		if (_next == null) {
			hasNext();
		}
		Integer ans = _next;
		_next = null;
		return ans;
	}

	static public class Test {
		static public void randomTest() {
			ArrayList<Integer> test = new ArrayList<>(Arrays.asList(-12, 2, 400, 2, 5, 10, 13, -44, -5, 3));
			FiveMultiplesInterator _solution = new FiveMultiplesInterator(test.iterator());
			while (_solution.hasNext()) {
				System.out.println(_solution.next());
			}
		}
	}
}
