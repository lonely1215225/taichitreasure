package edu.hunau.hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab=new HashTab( 8 );
        for (int i=0; i < 15; i++) {
            Node node=new Node( i, "张卓悦" + i );
            hashTab.add( node );
        }
        hashTab.list();
        System.out.println( hashTab.size );
        hashTab.findById( 14 );
        hashTab.deleteById( 14);
        hashTab.deleteById( 0);
        hashTab.deleteById( 1);
        hashTab.list();
    }
}

class HashTab {
    private LinkedListDemo[] linkedListArray;
    private int length;
    public int size;

    public HashTab(int length) {
        this.length=length;
        linkedListArray=new LinkedListDemo[length];
        for (int i=0; i < length; i++) {
            linkedListArray[i]=new LinkedListDemo();
        }
    }

    public void add(Node att) {
        linkedListArray[hashFun( att.getId() )].add( att );
        size++;
    }

    public void findById(int id) {
        Node byId=linkedListArray[hashFun( id )].findById( id );
        if (byId != null)
            System.out.printf( "=======id: %d 的姓名为(%s)", byId.getId(), byId.getName() );
        else
            System.out.println( "哈希表中未找到" );
        System.out.println();
    }

    public void deleteById(int id){
        int index=hashFun( id );
        System.out.println("=======已删除id为："+id+"信息");
        linkedListArray[index].deleteById( id );
    }
    public void list( ) {
        for (int i=0; i < length; i++) {
            linkedListArray[i].list( i );
        }
    }

    public int hashFun(int id) {
        return id & (length - 1);
    }
}

class LinkedListDemo {
    private Node head;

    public void add(Node att) {
        if (head == null) {
            head=att;
            return;
        }
       // Node cur=head;
        att.setNext( head.getNext() );
        head.setNext( att );
        //下面是尾插法，对于简单的hashtab插入效率不高
//        while (true) {
//            if (cur.getNext() == null)
//                break;
//            cur=cur.getNext();
//        }
//        cur.setNext( att );
    }

    public Node findById(int id) {
        if (head == null)
            return null;
        Node cur=head;
        while (true) {
            if (cur.getId() == id)
                break;
            if (cur.getNext() == null) {
                return null;
            }
            cur=cur.getNext();
        }
        return cur;
    }

    public void deleteById(int id){
        if (head == null) {
            System.out.println( "当前链表为空" );
            return;
        }
        if(head.getId()==id){
            head=head.getNext();
            return;
        }
        Node cur=head;
        while (true){
            if(cur.getNext().getId()==id)
                break;
            if (cur.getNext()==null) {
                System.out.println("未找到对应的id:"+id);
                return;
            }
            cur=cur.getNext();
        }
        cur.setNext( cur.getNext().getNext() );
    }
    public void list(int no) {
        if (head == null) {
            System.out.println( "第" + (no + 1) + "条链表为空" );
            return;
        }
        System.out.print( "当前为第" + (no + 1) + "链表的信息为：" );
        Node cur=head;
        while (true) {
            System.out.printf( "---> id=%d  name=%s\t", cur.getId(), cur.getName() );
            if (cur.getNext() == null)
                break;
            cur=cur.getNext();
        }
        System.out.println();
    }
}

class Node {
    private int id;
    private String name;
    private Node next;

    public Node(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public int getId( ) {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getName( ) {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Node getNext( ) {
        return next;
    }

    public void setNext(Node next) {
        this.next=next;
    }
}