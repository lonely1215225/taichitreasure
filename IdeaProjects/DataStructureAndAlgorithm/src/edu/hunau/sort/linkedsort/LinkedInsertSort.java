package edu.hunau.sort.linkedsort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LinkedInsertSort {
    private Node head = null;

    public static void main(String[] args) {
        //int[] nums = {2, 3, 4, 9, 1, 4, 3, 2,10};
        int[] nums = new int[80000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 10000000);
        }
        LinkedInsertSort link = new LinkedInsertSort(nums);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
        String beforetime = simpleDateFormat.format(new Date());
        System.out.println("before:" + beforetime);

        insertSort(link.head);

        Date date = new Date();
        String afterTime = simpleDateFormat.format(date);
        System.out.println("after:" + afterTime);

        //LinkedFactory.showLink(link.head);
    }

    public LinkedInsertSort(int[] nums) {
        Node head = LinkedFactory.createDoubleCircleLinked(nums);
        this.head = head;
    }

    public static void insertSort(Node head) {
        if (null == head.getNext()) {
            return;
        }
        Node temp = head.getNext();
        while (temp != head) {
            temp = temp.getNext();
            if (temp == head)
                break;
            int insertValue = temp.getValue();
            Node insertPointer = temp.getPre();
            while (head != insertPointer) {
                if (insertPointer.getValue() > insertValue && insertPointer.getPre().getValue() <= insertValue) {
                    insertPointer = insertPointer.getPre();

                    temp.getPre().setNext(temp.getNext());
                    temp.getNext().setPre(temp.getPre());

                    Node insertNode = new Node(insertValue);
                    insertNode.setNext(insertPointer.getNext());
                    insertPointer.getNext().setPre(insertNode);
                    insertPointer.setNext(insertNode);
                    insertNode.setPre(insertPointer);

                    continue;
                }
                insertPointer = insertPointer.getPre();
            }
        }
    }
}
