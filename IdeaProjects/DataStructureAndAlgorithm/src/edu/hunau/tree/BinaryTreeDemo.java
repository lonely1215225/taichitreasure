package edu.hunau.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        HeroNode heroNode=new HeroNode( 1, "÷���´�˹" );
        HeroNode heroNode1=new HeroNode( 2, "��˹��ŵ" );
        HeroNode heroNode2=new HeroNode( 3, "����ɯ��" );
        HeroNode heroNode3=new HeroNode( 4, "��ɪ" );
        HeroNode heroNode4=new HeroNode( 5, "ħ����" );
        heroNode.setLeft( heroNode1 );
        heroNode.setRight( heroNode3 );
        heroNode3.setLeft( heroNode2 );
        heroNode3.setRight( heroNode4 );
        binaryTree.setRoot( heroNode );
        System.out.println( "���������:" );
        binaryTree.preOrder();
        System.out.println( "�����������" );
        binaryTree.infixOrder();
        System.out.println( "�����������" );
        binaryTree.posterOrder();

        System.out.println( "����������ң�" );
        HeroNode resNode=binaryTree.preSearch( 3 );
        if (resNode != null)
            System.out.println( "id Ϊ " + resNode.getId() + "������Ϊ:" + resNode.getName() );
        else
            System.out.println( "�����ڵ�id" );
        System.out.println( "����������ң�" );
        HeroNode resNode1=binaryTree.infixSearch( 3 );
        if (resNode != null)
            System.out.println( "id Ϊ " + resNode.getId() + "������Ϊ:" + resNode.getName() );
        else
            System.out.println( "�����ڵ�id" );
        System.out.println( "����������ң�" );
        HeroNode resNode2=binaryTree.posterSearch( 3 );
        if (resNode != null)
            System.out.println( "id Ϊ " + resNode.getId() + "������Ϊ:" + resNode.getName() );
        else
            System.out.println( "�����ڵ�id" );

        System.out.println( "����ɾ��idΪ4��" );
        binaryTree.preSearchDel( 4 );
        binaryTree.preOrder();
    }
}

class BinaryTree {
    protected HeroNode root;

    public void setRoot(HeroNode root) {
        this.root=root;
    }

    public void preOrder( ) {
        if (root != null)
            root.preOrder();
        else
            System.out.println( "���ڵ�Ϊ��" );
    }

    public void infixOrder( ) {
        if (root != null)
            root.infixOrder();
        else
            System.out.println( "���ڵ�Ϊ��" );
    }

    public void posterOrder( ) {
        if (root != null)
            root.posterOrder();
        else
            System.out.println( "���ڵ�Ϊ��" );
    }

    public HeroNode preSearch(int id) {
        if (root != null)
            return root.preSearch( id );
        else
            System.out.println( "���ڵ�Ϊ��" );
        return null;
    }

    public HeroNode infixSearch(int id) {
        if (root != null)
            return root.infixSearch( id );
        else
            System.out.println( "���ڵ�Ϊ��" );
        return null;
    }

    public HeroNode posterSearch(int id) {
        if (root != null)
            return root.posterSearch( id );
        else
            System.out.println( "���ڵ�Ϊ��" );
        return null;
    }

    public void preSearchDel(int id) {
        if (root != null)
            if (root.getId() == id)
                root=null;
            else
                root.preSearchDel( id );
        else
            System.out.println( "���ڵ�Ϊ��" );
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
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

    public HeroNode getLeft( ) {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left=left;
    }

    public HeroNode getRight( ) {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right=right;
    }

    @Override
    public String toString( ) {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void preOrder( ) {
        System.out.println( this );
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    public void infixOrder( ) {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println( this );
        if (this.right != null)
            this.right.infixOrder();
    }

    public void posterOrder( ) {
        if (this.left != null)
            this.left.posterOrder();
        if (this.right != null)
            this.right.posterOrder();
        System.out.println( this );
    }

    public HeroNode preSearch(int id) {
        System.out.println( "�������" );
        if (this.getId() == id)
            return this;
        HeroNode resNode=null;
        if (this.left != null)
            resNode=this.left.preSearch( id );
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode=this.right.preSearch( id );
        return resNode;
    }

    public HeroNode infixSearch(int id) {
        HeroNode resNode=null;
        if (this.left != null)
            resNode=this.left.infixSearch( id );
        if (resNode != null)
            return resNode;
        System.out.println( "�����ѯ" );
        if (this.getId() == id)
            return this;
        if (this.right != null)
            resNode=this.right.infixSearch( id );
        return resNode;
    }

    public HeroNode posterSearch(int id) {
        HeroNode resNode=null;
        if (this.left != null)
            resNode=this.left.posterSearch( id );
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode=this.right.posterSearch( id );
        if (resNode != null)
            return resNode;
        System.out.println( "�����ѯ" );
        if (this.getId() == id)
            return this;
        return resNode;
    }

    public void preSearchDel(int id) {
        System.out.println( "�������ɾ��" );
        HeroNode temp=null;
        if (this.left != null && this.left.getId() == id) {
            if (this.left.left != null) {
                temp=this.left.right;
                this.left=this.left.left;
                this.left.right=temp;
                this.left.left=null;
            }
            return;
        }
        if (this.right != null && this.right.getId() == id) {
            if (this.right.left != null) {
                temp=this.right.right;
                this.right=this.right.left;
                this.right.right=temp;
                this.right.left=null;
            }
            return;
        }
        if (this.left != null)
            this.left.preSearchDel( id );
        if (this.right != null)
            this.right.preSearchDel( id );

    }
}