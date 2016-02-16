package solutions;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A thread safe queue class free from mutex locks. Atomic CAS
 * operation is employed.
 * <p>
 * Created by feiyi.zhan on 2/15/16.
 */
public class LockFreeQueue<T> {
	class LinkedListNode<V> {
		final V value;
		AtomicReference<LinkedListNode<V>> next;

		LinkedListNode(V v) {
			value = v;
			next = new AtomicReference<>(null);
		}
	}

	private AtomicReference<LinkedListNode<T>> head;
	private AtomicReference<LinkedListNode<T>> tail;

	LockFreeQueue() {
		LinkedListNode<T> dummy = new LinkedListNode<>(null);
		head = new AtomicReference<>(dummy);
		tail = new AtomicReference<>(dummy);
	}

	public boolean empty() {
		return head.get().next.get() == null;
	}

	public void enqueue(T elem) {
		LinkedListNode<T> node = new LinkedListNode<>(elem);
		LinkedListNode<T> prevTail = tail.get();
		do {
			while (prevTail.next.get() != null) {
				prevTail = prevTail.next.get();
			}
		} while (!prevTail.next.compareAndSet(null, node));
		tail.compareAndSet(prevTail, node);
	}

	public T dequeue() {
		LinkedListNode<T> prevHead;
		do {
			prevHead = head.get().next.get();
			if (prevHead == null) {
				throw new NoSuchElementException();
			}
		} while (!head.get().next.compareAndSet(prevHead, prevHead.next.get()));
		return prevHead.value;
	}
}
