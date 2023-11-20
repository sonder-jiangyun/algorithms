package linked.p876;

import linked.ListNode;

public class Solution {

    public ListNode middleNode(ListNode head) {
        ListNode pre = new ListNode(-1);
        pre.next = head;

        ListNode slow = pre;
        ListNode fast = pre;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        return slow.next;
    }
}
