package solutions;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by feiyi.zhan on 1/6/16.
 */
public class MedianFinder {
	private PriorityQueue<Integer> _maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	private PriorityQueue<Integer> _minHeap = new PriorityQueue<>();

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (_minHeap.isEmpty() || num > _minHeap.peek()) _minHeap.add(num);
		else _maxHeap.add(num);
		while (_maxHeap.size() - _minHeap.size() > 1) _minHeap.add(_maxHeap.poll());
		while (_minHeap.size() - _maxHeap.size() > 1) _maxHeap.add(_minHeap.poll());
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (_maxHeap.size() == _minHeap.size())
			return (_maxHeap.peek() + (double) _minHeap.peek()) / 2.0;
		else if (_maxHeap.size() > _minHeap.size())
			return _maxHeap.peek();
		else
			return _minHeap.peek();
	}

	static public class Test {
		static public void randomTest() {
			MedianFinder testOne = new MedianFinder();
			testOne.addNum(1);
			testOne.addNum(2);
			testOne.addNum(2);
			testOne.addNum(1);
			System.out.println(testOne.findMedian());
		}
	}
}
