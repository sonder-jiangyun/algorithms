package linked.p23;

import linked.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    //分治解法
    public ListNode mergeLists(ListNode[] lists) {
        return this.doMergeList(lists, 0, lists.length - 1);
    }

    private ListNode doMergeList(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        if (left > right) {
            return null;
        }

        int mid = left + right >> 1;

        return this.doMerge(
                doMergeList(lists, left, mid),
                doMergeList(lists, mid + 1, right));
    }

    private ListNode doMerge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }

        ListNode cursor = new ListNode(-1);
        ListNode pre = cursor;

        ListNode first = list1;
        ListNode second = list2;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                pre.next = first;
                first = first.next;
            } else {
                pre.next = second;
                second = second.next;
            }

            pre = pre.next;
        }

        pre.next = first != null ? first : second;
        return cursor.next;
    }

    //优先队列解法
    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if (length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(
                Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            queue.add(head);
        }

        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (!queue.isEmpty()) {
            ListNode curHead = queue.poll();
            pre.next = curHead;
            if (curHead.next != null) {
                queue.add(curHead.next);
            }
            pre = pre.next;
        }

        return head.next;
    }
}
