package edu.hunau.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] nums={13,7,8,3,29,6,1};
        System.out.println("前序遍历HuffmanTree：");
        preOrder(createHuffmanTree( nums ));
    }
    public static void preOrder(Node root){
        if (root!=null)
            root.preOrder();
        else
            System.out.println("根节点为空，无法遍历");
    }
    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes=new ArrayList<>();
        for(int value:arr)
            nodes.add( new Node(value) );
        while (nodes.size()>1) {
            Collections.sort( nodes );
            Node leftNode=nodes.get( 0 );
            Node rightNode=nodes.get( 1 );
            Node parent=new Node( leftNode.getValue() + rightNode.getValue() );
            parent.setLeft( leftNode );
            parent.setRight( rightNode );
            nodes.remove( leftNode );
            nodes.remove( rightNode);
            nodes.add( parent );
        }
        return nodes.get( 0 );
    }
}

class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value=value;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null)
            this.left.preOrder();
        if (this.right!=null)
            this.right.preOrder();
    }
    @Override
    public String toString( ) {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue( ) {
        return value;
    }

    public void setValue(int value) {
        this.value=value;
    }

    public Node getLeft( ) {
        return left;
    }

    public void setLeft(Node left) {
        this.left=left;
    }

    public Node getRight( ) {
        return right;
    }

    public void setRight(Node right) {
        this.right=right;
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }
}