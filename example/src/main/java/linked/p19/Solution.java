package linked.p19;

import linked.ListNode;

public class Solution {

    /**
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curNode = new ListNode(-1);
        //通过哨兵简化逻辑
        curNode.next = head;

        ListNode fastNode = curNode;
        //移动n+1个节点，获取倒数n的前一节点位置
        for (int i = 0; i < n + 1; i++) {
            fastNode = fastNode.next;
        }

        ListNode slowNode = curNode;
        while (fastNode != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        slowNode.next = slowNode.next.next;
        return curNode.next;
    }
}
