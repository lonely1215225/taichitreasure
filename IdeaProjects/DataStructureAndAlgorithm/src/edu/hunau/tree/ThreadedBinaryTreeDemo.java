package edu.hunau.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree1 binaryTree=new BinaryTree1();
        HeroNode1 heroNode=new HeroNode1( 1, "梅利奥达斯" );
        HeroNode1 heroNode1=new HeroNode1( 2, "艾斯卡诺" );
        HeroNode1 heroNode2=new HeroNode1( 3, "伊丽莎白" );
        HeroNode1 heroNode3=new HeroNode1( 4, "亚瑟" );
        HeroNode1 heroNode4=new HeroNode1( 5, "魔神王" );
        HeroNode1 heroNode5=new HeroNode1( 6, "king" );
        HeroNode1 heroNode6=new HeroNode1( 7, "狄安娜" );
        heroNode.setLeft( heroNode1 );
        heroNode.setRight( heroNode3 );
        heroNode1.setLeft( heroNode5 );
        heroNode3.setLeft( heroNode2 );
        heroNode3.setRight( heroNode4 );
        heroNode4.setLeft( heroNode6 );
        binaryTree.setRoot( heroNode );
        //中序线索二叉树
//        binaryTree.threadedInfix();
//        System.out.println( heroNode1 + "的左节点：" + heroNode1.getLeft() + "右节点：" + heroNode1.getRight() );
        //中序无递归遍历线索二叉树
//        binaryTree.threadList();
        //先序线索二叉树
//        binaryTree.threadedPre();
//        System.out.println( heroNode5 + "\n的左节点：\n" + heroNode5.getLeft() + "\n右节点：\n" + heroNode5.getRight() );
//        //先序无递归遍历线索二叉树
//        binaryTree.threadedPreList();
        //后序线索二叉树
        binaryTree.threadedPoster();
         System.out.println( heroNode4 + "\n的左节点：\n" + heroNode4.getLeft() + "\n右节点：\n" + heroNode4.getRight() );
        binaryTree.posterList();
    }
}

class BinaryTree1 {
    private HeroNode1 root;
    private HeroNode1 pre=null;

    public void setRoot(HeroNode1 root) {
        this.root=root;
    }

    public void threadedPre( ) {
        threadedPre( root );
    }

    public void threadedInfix( ) {
        threadedInfix( root );
    }

    public void threadedPoster( ) {
        threadedPoster( root );
    }

    public void threadedPre(HeroNode1 node) {
        if (node == null)
            return;
        if (node.getLeft() == null) {
            node.setLeft( pre );
            node.setLeftType( true );
        }
        if (pre != null && pre.getRight() == null) {
            // if (!node.isRightType()) {
            pre.setRight( node );
            pre.setRightType( true );
            //  }
        }
        pre=node;
        if (!node.isLeftType())
            threadedPre( node.getLeft() );

        if (!node.isRightType())
            threadedPre( node.getRight() );
    }

    public void threadedPreList( ) {
        HeroNode1 node=root;
        while (node != null) {
            while (!node.isLeftType()) {
                System.out.println( node );
                node=node.getLeft();
            }
            while (node.isRightType()) {
                System.out.println( node );
                node=node.getRight();
            }
            if (node.getRight() == null) {
                System.out.println( node );
                return;
            }
        }
    }

    public void threadedInfix(HeroNode1 node) {
        if (node == null)//用于递归限制到左右
            return;
        threadedInfix( node.getLeft() );//向左递归到底，即左叶子
        if (node.getLeft() == null) {//左叶子的左边为空
            node.setLeft( pre );
            node.setLeftType( true );
        }
        if (pre != null && pre.getRight() == null) {//判断上一次递归回来的pre的右边是否为空并且pre是否为空
            pre.setRight( node );
            pre.setRightType( true );
        }//将上次递归回的pre指向回溯回来的当前node
        pre=node;//当前的node赋给pre，为了回溯回去的pre与node连接
        threadedInfix( node.getRight() );//右递归
    }

    public void threadList( ) {
        HeroNode1 node=root;
        while (node != null) {
            while (!node.isLeftType())
                node=node.getLeft();
            System.out.println( node );
            while (node.isRightType()) {
                node=node.getRight();
                System.out.println( node );
            }
            node=node.getRight();
        }
    }

    public void threadedPoster(HeroNode1 node) {
        if (node == null)
            return;
        threadedPoster( node.getLeft() );
        threadedPoster( node.getRight() );
        if (node.getLeft() == null) {
            node.setLeft( pre );
            node.setLeftType( true );
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight( node );
            pre.setRightType( true );
        }
        pre=node;
    }

    public void posterList( ) {
        HeroNode1 node=root;
            while (!node.isLeftType())
                node=node.getLeft();
            while (node.isRightType()){
                System.out.println(node);
                node=node.getRight();
            }
            System.out.println(node);
            System.out.println(root);
    }
}

class HeroNode1 {
    private int id;
    private String name;
    private HeroNode1 left;
    private HeroNode1 right;
    private boolean leftType=false;
    private boolean rightType=false;

    public boolean isLeftType( ) {
        return leftType;
    }

    public void setLeftType(boolean leftType) {
        this.leftType=leftType;
    }

    public boolean isRightType( ) {
        return rightType;
    }

    public void setRightType(boolean rightType) {
        this.rightType=rightType;
    }

    public HeroNode1(int id, String name) {
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

    public HeroNode1 getLeft( ) {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left=left;
    }

    public HeroNode1 getRight( ) {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right=right;
    }

    @Override
    public String toString( ) {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}