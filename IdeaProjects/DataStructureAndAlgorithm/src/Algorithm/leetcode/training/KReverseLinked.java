package Algorithm.leetcode.training;

public class KReverseLinked {
    public static void main(String[] args) {
        ListNode node=new ListNode(1);
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(3);
        ListNode node3=new ListNode(4);
        ListNode node4=new ListNode(5);
        node.next=node1;node1.next=node2;node2.next=node3;node3.next=node4;
        //reverseKGroup(node,3);
        ListNode listNode = reverseKGroupBetter(node, 3);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }

    }
    public static  ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp=head;
        ListNode newNode=new ListNode(-1);
        ListNode temp1=newNode;
        ListNode temp2=null;
        int count=0,rest=0;
        for(;;){
            if(temp==null) break;
            if(count==k){
                rest=0;
                while(temp1.next!=null) {temp1=temp1.next;}
                temp2=temp;
                while(temp2.next!=null){temp2=temp2.next;
                    rest++;
                }
                if(rest<k) break;
                count=0;
            }
            else{
                count++;
                ListNode next=temp.next;
                temp.next=temp1.next;
                temp1.next=temp;
                temp=next;
            }
        }
        while(temp!=null){
            temp1.next=temp;
            temp=temp.next;
            temp1=temp1.next;
        }
        return newNode.next;
    }

    public static ListNode reverseKGroupBetter(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
class ListNode {
     int val;
 ListNode next;
     ListNode(int x) { val = x; }
 }