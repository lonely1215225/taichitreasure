package edu.hunau.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayBinaryTree arrayBinaryTree=new ArrayBinaryTree( arr );
        System.out.println("数组先序遍历");
        arrayBinaryTree.preOrder();
        System.out.println();
        System.out.println("数组中序遍历");
        arrayBinaryTree.infixOrder( );
        System.out.println();
        System.out.println("数组后序遍历");
        arrayBinaryTree.posterOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr=arr;
    }

    public void preOrder( ) {
        preOrder( 0 );
    }
    public void infixOrder(){
        infixOrder( 0 );
    }
    public void posterOrder(){
        posterOrder( 0 );
    }
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println( "数组为空" );
        }
        System.out.print(arr[index]+" ");
        //向左递归
        if ((2 * index + 1) < arr.length)
            preOrder( 2 * index + 1 );
        //向右递归
        if ((2 * index + 2) < arr.length)
            preOrder( 2 * index + 2 );
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println( "数组为空" );
        }
        //向左递归
        if ((2 * index + 1) < arr.length)
            infixOrder( 2 * index + 1 );
        System.out.print( arr[index]+" ");
        //向右递归
        if ((2 * index + 2) < arr.length)
            infixOrder( 2 * index + 2 );
    }

    public void posterOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println( "数组为空" );
        }
        //向左递归
        if ((2 * index + 1) < arr.length)
            posterOrder( 2 * index + 1 );
        //向右递归
        if ((2 * index + 2) < arr.length)
            posterOrder( 2 * index + 2 );
        System.out.print( arr[index]+" ");
    }

}