package Algorithm.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DetectCycle {
    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(-4);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(2);
        node4.next = node5;
        node5.next = node2;
//        ListNode detectedNode = detectCycle(node);
//        while (detectedNode != null) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(detectedNode.val);
//            detectedNode = detectedNode.next;
//        }
        System.out.println(getIntersectionNode(node, node4).val);
    }

    //    输入：head = [3,2,0,-4], pos = 1
//    输出：tail connects to node index 1
//    解释：链表中有一个环，其尾部连接到第二个节
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode temp = headA;
        int index = 0;
        while (temp != null) {
            map.put(index++, temp);
            temp = temp.next;
        }
        ListNode temp1 = headB;
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            temp1=headB;
            while (temp1 != null) {
                if (temp1 == map.get(key)) return temp1;
                else
                    temp1 = temp1.next;
            }
        }
        return null;
    }
}