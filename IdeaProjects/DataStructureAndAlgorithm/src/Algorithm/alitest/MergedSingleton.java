package Algorithm.alitest;
public class MergedSingleton{
    public static void main(String args[]){
        Node node=new Node(1);
        Node node1=new Node(4);
        Node node2=new Node(6);
        Node node3=new Node(9);
        node.next=node1;
        node1.next=node2;
        node2.next=node3;
        Node noded=new Node(0);
        Node noded1=new Node(5);
        Node noded2=new Node(7);
        Node noded3=new Node(8);
        Node noded4=new Node(12);
        noded.next=noded1;
        noded1.next=noded2;
        noded2.next=noded3;
        noded3.next=noded4;
        Node mergedNode=mergedLinked(node,noded);
        while(mergedNode!=null){
            System.out.println(mergedNode.val);
            mergedNode=mergedNode.next;
        }
    }
    public static Node mergedLinked(Node node1,Node node2){
        Node t1=node1;
        Node t2=node2;
        Node mergedNode=new Node(0);
        Node tm=mergedNode;
        //Node next=null;
        if (t1==null&&t2==null) return mergedNode.next;
        if(t1==null||t2==null){
            mergedNode=t1==null?t2:t1;
            return mergedNode.next;
        }
        while(true){
            if(t1==null&&t2==null) break;
            if(t1==null&&t2!=null){
                tm.next=t2; break;
            }
            if(t1!=null&&t2==null){
                tm.next=t1;break;
            }
            if(t1!=null&&t2!=null){
                if(t1.val<t2.val){
                    //next=t1.next;
                    tm.next=t1;
                    t1=t1.next;
                }else{
//                    next=t2.next;
                    tm.next=t2;
                    t2=t2.next;
                }
            }
            tm=tm.next;
        }
        return mergedNode.next;
    }
}
class Node{
     int val;
     Node next;
    public Node(int val){this.val=val;}
}