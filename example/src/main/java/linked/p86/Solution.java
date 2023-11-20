package linked.p86;

import linked.ListNode;

public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode part1 = new ListNode(-1);
        ListNode head1 = part1;

        ListNode part2 = new ListNode(-1);
        ListNode head2 = part2;

        ListNode cursor = head;
        while (cursor != null) {
            ListNode temp = cursor.next;
            if (cursor.val < x) {
                head1.next = cursor;
                head1 = head1.next;
            } else {
                head2.next =cursor;
                head2 = head2.next;
            }

            cursor.next = null;
            cursor = temp;
        }

        head1.next = part2.next;
        return part1.next;
    }


}
