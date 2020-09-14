package edu.hunau.avl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8};
//        int[] arr={10,12,8,9,7,6};
        int[] arr={10,11,7,6,8,9,9};
        AVLTree avlTree=new AVLTree();
        for (int val : arr) {
            avlTree.add( new Node( val ) );
        }
        avlTree.infixOrder();
        System.out.println("树高度："+avlTree.getRoot().height() );
        System.out.println( "左子树高度："+avlTree.getRoot().leftHeight() );
        System.out.println( "右子树高度："+avlTree.getRoot().rightHeight(  ));
        System.out.println("root结点："+ avlTree.getRoot() );
        System.out.println("root结点左结点："+ avlTree.getRoot().right.left);
        avlTree.infixOrder();
    }

}

class AVLTree {
   private Node root;

    public Node getRoot( ) {
        return root;
    }

    public void add(Node node) {
        if (root == null)
            root=node;
        else
            root.add( node );
//        if (root.rightHeight()-root.leftHeight()>1)
//            leftRotate( );
    }

    public void infixOrder( ) {
        if (root != null)
            root.infixOrder();
        else
            System.out.println( "root空" );
    }
    //左旋二
//    public void leftRotate(){
//        Node temp=root.right;
//        root.right=temp.left;
//        temp.left=root;
//        root=temp;
//    }
}

class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value=value;
    }

    //左旋一
    public void leftRotate(){
        Node newNode=new Node( value );
        newNode.left=left;
        newNode.right=right.left;
        value=right.value;
        left=newNode;
        right=right.right;
    }

    public void rightRotate(){
        Node newNode=new Node( value );
        newNode.left=left.right;
        newNode.right=right;
        value=left.value;
        left=left.left;
        right=newNode;
    }

    public int leftHeight() {
        if (this.left != null)
            return this.left.height( );
        else
            return 0;
    }

    public int rightHeight() {
        if (this.right != null)
            return this.right.height();
        else
            return 0;
    }

    public int height() {
        return Math.max( this.left == null ? 1 : this.left.height(  ) + 1,
                this.right == null ? 1 : this.right.height(  ) + 1 );
    }

    //构建二叉排序树
    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null)
                this.left=node;
            else
                this.left.add( node );
        } else {
            if (this.right == null)
                this.right=node;
            else
                this.right.add( node );
        }
        if (rightHeight()-leftHeight()>1) {
            if (right.leftHeight() > right.rightHeight())
                right.rightRotate();
            leftRotate();
        }
        if (leftHeight()-rightHeight()>1) {
            if (left.rightHeight() > left.leftHeight())
                left.leftRotate();
            rightRotate();
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value))
            return this;
        else if (value < this.value && this.left != null)
            return this.left.searchParent( value );
        else if (value > this.value && this.right != null)
            return this.right.searchParent( value );
        else
            return null;//没有找到父节点
    }

    public Node search(int value) {
        if (value == this.value)
            return this;
        else if (value < this.value) {
            if (this.left != null)
                return this.left.search( value );
        } else {
            if (this.right != null)
                return this.right.search( value );
        }
        return null;
    }

    public void infixOrder( ) {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println( this );
        if (this.right != null)
            this.right.infixOrder();

    }

    @Override
    public String toString( ) {
        return "Node{" +
                "value=" + value +
                '}';
    }
}