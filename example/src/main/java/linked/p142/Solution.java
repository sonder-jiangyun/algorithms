package linked.p142;

import linked.ListNode;

public class Solution {

    /**
     * 检测环的起始点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        //检测是否存在环，并定位到环的位置，初次相遇不是环的起点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        //判断没有环
        if (fast == null || fast.next == null) {
            return null;
        }

        //慢指针重新指向head,再次相遇则是环的起点
        //slow == fast(初次相遇)
        // 假设 slow 走 r + l, 则 fast 走 2 * (r+l)
        //slow 与fast 初次相遇的节点，再移动r则是环的起始点;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
