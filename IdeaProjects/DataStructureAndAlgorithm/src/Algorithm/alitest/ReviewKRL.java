package Algorithm.alitest;

public class ReviewKRL {
    public static void main(String[] args) {
        ListNode1 node = new ListNode1(1);
        ListNode1 node1 = new ListNode1(2);
        ListNode1 node2 = new ListNode1(3);
        ListNode1 node3 = new ListNode1(4);
        ListNode1 node4 = new ListNode1(5);
        ListNode1 node5 = new ListNode1(6);
        ListNode1 node6 = new ListNode1(7);
        ListNode1 node7 = new ListNode1(8);
        ListNode1 node8 = new ListNode1(9);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next=node7;
        node7.next=node8;
        ListNode1 ListNode1 = reverseKGroupBetter(node, 3);
        while (ListNode1 != null) {
            System.out.println(ListNode1.val);
            ListNode1 = ListNode1.next;
        }
    }

    private static ListNode1 reverseKGroupBetter(ListNode1 node, int i) {
        ListNode1 dummy = new ListNode1(0);
        dummy.next = node;
        ListNode1 pre = dummy;
        ListNode1 next = null;
        ListNode1 end = dummy;
        ListNode1 start = node;

        while (true) {
            for (int j = 0; j < i; j++) {
                end = end.next;
                if (end == null) return dummy;
            }
            if (end != null) {
                next = end.next;
                end.next = null;
            } else {
                next = null;
            }

            ListNode1 rev = reverse(start);
            ListNode1 tee = rev;
            pre.next = rev;
            while (pre.next != null) pre = pre.next;
            while (tee.next != null) tee = tee.next;
            tee.next = next;
            start = next;
            end = tee;
        }
    }

    private static ListNode1 reverse(ListNode1 start) {

        ListNode1 rev = new ListNode1(0);
        ListNode1 tem = rev;
        ListNode1 tem2 = start;
        ListNode1 next = null;
        while (true) {
            if (tem2 == null)
                break;
            next = tem2.next;
            tem2.next = tem.next;
            tem.next = tem2;
            tem2 = next;
        }
        return rev.next;
    }
}

class ListNode1 {
    int val;
    ListNode1 next;

    ListNode1(int x) {
        val = x;
    }
}