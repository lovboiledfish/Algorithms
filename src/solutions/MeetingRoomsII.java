package solutions;

import com.sun.tools.javac.util.Assert;
import solutions.utils.Interval;

/**
 * Created by PPlovboiledfish on 11/9/15.
 */
public class MeetingRoomsII {
    static private class Node {
        Interval interval;
        int overlapCnt;
        Node next;

        public Node(Interval i) {
            interval = i;
            overlapCnt = 1;
            next = null;
        }
    }

    private boolean overlapped(Interval i1, Interval i2) {
        return (i1.end > i2.start && i2.end > i1.start);
    }

    private Interval split(Node prev, Interval interval) {
        Node cur = prev.next;
        if (interval.start > cur.interval.start) {
            if (interval.end < cur.interval.end) {
                Interval rear = new Interval(interval.end, cur.interval.end);
                cur.interval.end = interval.start;
                Node next = cur.next;
                cur.next = new Node(interval);
                cur.next.overlapCnt = cur.overlapCnt + 1;
                cur.next.next = new Node(rear);
                cur.next.next.next = next;
                return null;
            } else {
                Interval overlap = new Interval(interval.start, cur.interval.end);
                Interval rear = null;
                if (cur.interval.end != interval.end)
                    rear = new Interval(cur.interval.end, interval.end);
                cur.interval.end = overlap.start;
                Node next = cur.next;
                cur.next = new Node(overlap);
                cur.next.overlapCnt = cur.overlapCnt + 1;
                cur.next.next = next;
                return rear;
            }
        } else {
            if (interval.end > cur.interval.end) {
                Interval front = new Interval(interval.start, cur.interval.start);
                Interval rear = new Interval(cur.interval.end, interval.end);
                ++cur.overlapCnt;
                if (front.start != front.end) {
                    prev.next = new Node(front);
                    prev.next.next = cur;
                }
                if (rear.start != rear.end) {
                    return rear;
                } else
                    return null;
            } else {
                Interval overlap = new Interval(cur.interval.start, interval.end);
                cur.interval.start = overlap.end;
                interval.end = overlap.start;
                if (interval.start != interval.end) {
                    prev.next = new Node(interval);
                    prev.next.next = cur;
                    prev = prev.next;
                }
                prev.next = new Node(overlap);
                prev.next.overlapCnt = cur.overlapCnt + 1;
                prev.next.next = cur;
                return null;
            }
        }
    }

    private void push(Node head, Interval interval) {
        Node prev = head, cur = prev.next;
        boolean inserted = false;
        while (cur != null) {
            if (overlapped(cur.interval, interval)) {
                Interval left = split(prev, interval);
                if (left != null)
                    push(prev, left);
                return;
            } else if (cur.interval.start >= interval.end) {
                prev.next = new Node(interval);
                prev.next.next = cur;
                inserted = true;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        if (!inserted)
            prev.next = new Node(interval);
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        Node timeline = new Node(null);
        for (Interval interval : intervals) {
            push(timeline, interval);
        }
        Node cur = timeline.next;
        int ret = -1;
        while (cur != null) {
            ret = (cur.overlapCnt > ret)? cur.overlapCnt : ret;
            cur = cur.next;
        }
        return ret;
    }

    static public class Test {
        static MeetingRoomsII _solution = new MeetingRoomsII();

        static public void test(Interval[] intervals, int ans) {
            Assert.check(_solution.minMeetingRooms(intervals) == ans);
        }

        static public void randomTest() {
            Interval[] intervals1 = {new Interval(5, 10), new Interval(0, 7), new Interval(15, 20)};
            test(intervals1, 2);
            Interval[] intervals2 = {new Interval(13, 15), new Interval(1, 13), new Interval(6, 9)};
            test(intervals2, 2);
            Interval[] intervals3 = {new Interval(8, 17), new Interval(5, 15), new Interval(6, 20)};
            test(intervals3, 3);
        }
    }
}
