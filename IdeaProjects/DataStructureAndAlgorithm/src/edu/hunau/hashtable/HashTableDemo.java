package edu.hunau.hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab=new HashTab( 8 );
        for (int i=0; i < 15; i++) {
            Node node=new Node( i, "��׿��" + i );
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
            System.out.printf( "=======id: %d ������Ϊ(%s)", byId.getId(), byId.getName() );
        else
            System.out.println( "��ϣ����δ�ҵ�" );
        System.out.println();
    }

    public void deleteById(int id){
        int index=hashFun( id );
        System.out.println("=======��ɾ��idΪ��"+id+"��Ϣ");
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
        //������β�巨�����ڼ򵥵�hashtab����Ч�ʲ���
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
            System.out.println( "��ǰ����Ϊ��" );
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
                System.out.println("δ�ҵ���Ӧ��id:"+id);
                return;
            }
            cur=cur.getNext();
        }
        cur.setNext( cur.getNext().getNext() );
    }
    public void list(int no) {
        if (head == null) {
            System.out.println( "��" + (no + 1) + "������Ϊ��" );
            return;
        }
        System.out.print( "��ǰΪ��" + (no + 1) + "�������ϢΪ��" );
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