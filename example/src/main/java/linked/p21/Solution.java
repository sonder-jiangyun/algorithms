package linked.p21;

import linked.ListNode;

public class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return this.doMerge(list1, list2);
    }

    private ListNode doMerge2(ListNode list1, ListNode list2) {
        ListNode mergeResult = new ListNode(-1);
        ListNode p = mergeResult;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }

            p = p.next;
        }

        p.next = list1 != null ? list1 : list2;
        return mergeResult.next;
    }

    private ListNode doMerge(ListNode list1, ListNode list2) {
        ListNode mergeHead = new ListNode(-1);
        ListNode p = mergeHead;

        while (list1 != null || list2 != null) {
            int d1 = list1 == null ? Integer.MAX_VALUE : list1.val;
            int d2 = list2 == null ? Integer.MAX_VALUE : list2.val;
            if (d1 <= d2) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
        }
        return mergeHead.next;
    }

}
