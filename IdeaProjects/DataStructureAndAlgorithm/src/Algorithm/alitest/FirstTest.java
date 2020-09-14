package Algorithm.alitest;

import java.util.Scanner;

public class FirstTest {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] locals = new int[n];
//        for (int i = 0; i < n; i++) {
//            locals[i] = sc.nextInt();
//        }
//        System.out.println(minPath(n, locals));
        HearNode node = new HearNode(0);
        HearNode node1 = new HearNode(3);
        HearNode node2= new HearNode(5);
        HearNode node3 = new HearNode(7);
        node.next=node1;
        node1.next=node2;
        node2.next=node3;
        HearNode node4 = new HearNode(1);
        HearNode node5 = new HearNode(2);
        HearNode node6= new HearNode(4);
        HearNode node7 = new HearNode(8);
        HearNode node8 = new HearNode(6);
        node4.next=node5;
        node5.next=node6;
        node6.next=node8;
        node8.next=node7;


        HearNode concat = concat(node, node4);
        while (concat!=null) {
            System.out.println(concat.no);
            concat=concat.next;
        }

    }

    public static int minPath(int n, int[] local) {
        int min = Integer.MAX_VALUE;
        int count = 0;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = Math.abs(local[i] - local[j]);
                }
            }
        }
        for (int[] g : graph) {
            for (int data : g) {
                count += data;
            }
            if (count < min)
                min = count;
            count = 0;
        }
        return min;
    }

    public static HearNode concat(HearNode head1,HearNode head2) {
        HearNode mergedNode = new HearNode(0);
        HearNode nt=mergedNode;
        if (head1==null||head2==null) {
            mergedNode = head1 == null ? head2 : head1;
            return mergedNode;
        }
        HearNode next=null;
        HearNode t1=head1;
        HearNode t2=head2;
        while (true){
            if (t1==null&&t2==null)
                break;
            if (t1==null&&t2!=null){
                nt.next=t2;
                t2=t2.next;
            }else if (t1!=null&&t2==null){
                nt.next=t1;
                t1=t1.next;
            }
            if (t1!=null&&t2!=null){
                if (t1.no<t2.no){
                    //next=t1.next;
                    nt.next=t1;
                    //t1=next;
                    t1=t1.next;
                }else {
                    //next=t2.next;
                    nt.next=t2;
                    //t2=next;
                    t2=t2.next;
                }
                nt=nt.next;
            }
        }
        return mergedNode;
    }
}
class HearNode{
    HearNode next;
    int no;

    public HearNode(int i) {
        this.no=i;
    }
}