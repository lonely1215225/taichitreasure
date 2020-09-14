package edu.hunau.sort.linkedsort;

public class LinkedFactory {
    static Node head=new Node(-1);
    static int size=0;
    public static Node createDoubleCircleLinked(int[] nums){
        Node temp = head;
        head.setNext(head);
        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(nums[i]);
            temp.setNext(node);
            node.setNext(head);
            node.setPre(temp);
            temp = temp.getNext();
            head.setPre(temp);
            size++;
        }
        return head;
    }

    public static void showLink(Node node) {
        if (null == node.getNext()) {
            return;
        }
        //System.out.println(node);
        Node temp = node.getNext();
        while (head != temp) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }
}
