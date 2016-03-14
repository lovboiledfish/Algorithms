package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TS onsite interview question: _find timestamp pairs in two streams (iterators)
 * whose difference is less than 1.
 * <p>
 * Created by feiyi.zhan on 3/12/16.
 */
public class FindCloseTimestamps {
	private Iterator<Float> _one;
	private Iterator<Float> _two;

	private Queue<Float> _qOne = new LinkedList<>();
	private Queue<Float> _qTwo = new LinkedList<>();
	private List<float[]> _res = new ArrayList<>();
	private ReentrantLock _lock = new ReentrantLock();
	private Thread t1 = new Thread() {
		@Override
		public void run() {
			while (_one.hasNext()) {
				_find(_qOne, _qTwo, _one);
			}
		}
	};
	private Thread t2 = new Thread() {
		@Override
		public void run() {
			while (_two.hasNext()) {
				_find(_qTwo, _qOne, _two);
			}
		}
	};

	public FindCloseTimestamps(Iterator<Float> o, Iterator<Float> t) {
		_one = o;
		_two = t;
	}

	private void _find(Queue<Float> q1, Queue<Float> q2, Iterator<Float> it) {
		if (it.hasNext()) {
			float val = it.next();
			_lock.lock();
			try {
				q1.offer(val);
				if (!q2.isEmpty()) {
					while (!q2.isEmpty() && Math.abs(q2.peek() - val) >= 1) {
						q2.poll();
					}
					for (float t : q2) {
						if (Math.abs(t - val) < 1) {
							_res.add(new float[]{val, t});
						} else {
							break;
						}
					}
				}
			} finally {
				_lock.unlock();
			}
		}
	}

	public List<float[]> findTimestampPairs() {
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return _res;
	}

	static public class Test {
		static public void randomTest() {
			List<Float> l1 = new ArrayList<>(Arrays.asList(0.2f, 1.4f, 3.0f));
			List<Float> l2 = new ArrayList<>(Arrays.asList(1.0f, 1.1f, 3.5f));
			FindCloseTimestamps process = new FindCloseTimestamps(l1.iterator(), l2.iterator());
			process.findTimestampPairs()
					.forEach(pair -> System.out.println(pair[0] + ", " + pair[1]));
		}
	}
}
