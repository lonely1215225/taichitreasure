package edu.hunau.sort.linkedsort;

public class LinkedBubbleSort {
    private Node head = null;
    private int size=0;
    int count=0;
    public static void main(String[] args) {
        int[] nums = {2, 7, 4, 9, 1, 4, 3, 2, 0};
        //int[] nums = new int[30000];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = (int) (Math.random() * 1000000);
//        }
        LinkedBubbleSort bubbleSort = new LinkedBubbleSort(nums);
        bubbleSort.bubbleSort(bubbleSort.head);
        LinkedFactory.showLink(bubbleSort.head);
        //bubbleSort.showLink(head);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
//        String beforetime = simpleDateFormat.format(new Date());
//        System.out.println("before:" + beforetime);
//
//        bubbleSort.bubbleSort(head);
//        //bubbleSort.showLink(head);
//        //System.out.println("Ñ­»·´ÎÊý£º"+count);
//
//        Date date = new Date();
//        String afterTime = simpleDateFormat.format(date);
//        System.out.println("after:" + afterTime);
    }


    public LinkedBubbleSort(int[] nums) {
        Node head = LinkedFactory.createDoubleCircleLinked(nums);
        this.head=head;
        this.size=LinkedFactory.size;
    }



    public void bubbleSort(Node head) {
        if (null == head.getNext()) {
            throw new RuntimeException("head getnext is null");
        }
        boolean flag = false;
        for (int i = 0; i < size; i++) {
           //count++;
            Node temp = head;
            while (temp.getNext() != head) {
                Node n1 = temp;
                Node n2 = temp.getNext();
                if (n1.getValue() > n2.getValue()) {
                    swap(n1, n2);
                    flag = true;
                    continue;
                }
                temp = temp.getNext();
            }
            if (!flag) {
                return;
            } else {
                flag = false;
            }
        }
    }

    public void swap(Node node1, Node node2) {
        node1.getPre().setNext(node2);
        node2.setPre(node1.getPre());
        node1.setNext(node2.getNext());
        node2.getNext().setPre(node1);
        node1.setPre(node2);
        node2.setNext(node1);
    }
}
