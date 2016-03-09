package solutions;import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by feiyi.zhan on 2/19/16.
 */
public class ConcurrentLRUCache<K, V> {
	private final int size;
	private Queue<K> _timing = new LinkedList<K>();
	private HashMap<K, V> _index = new HashMap<K, V>();

	private ReentrantLock readLock = new ReentrantLock();
	private ReentrantLock writeLock = new ReentrantLock();

	public ConcurrentLRUCache(int s) {
		if (s <= 0) {
			throw new IllegalArgumentException();
		}
		size = s;
	}

	public V get(K key) {
		readLock.lock();
		try {
			return _index.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public void put(K key, V val) {
		writeLock.lock();
		try {
			if (get(key) == null) {
				_timing.offer(key);
			}
			_index.put(key, val);
			if (_index.size() > size) {
				K popped = _timing.poll();
				_index.remove(popped);
			}
		} finally {
			writeLock.unlock();
		}
	}

	public int size() {
		readLock.lock();
		try {
			return _index.size();
		} finally {
			readLock.unlock();
		}
	}
}
