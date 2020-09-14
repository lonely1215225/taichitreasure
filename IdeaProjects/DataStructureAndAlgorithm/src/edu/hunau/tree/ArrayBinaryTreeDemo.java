package edu.hunau.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayBinaryTree arrayBinaryTree=new ArrayBinaryTree( arr );
        System.out.println("�����������");
        arrayBinaryTree.preOrder();
        System.out.println();
        System.out.println("�����������");
        arrayBinaryTree.infixOrder( );
        System.out.println();
        System.out.println("����������");
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
            System.out.println( "����Ϊ��" );
        }
        System.out.print(arr[index]+" ");
        //����ݹ�
        if ((2 * index + 1) < arr.length)
            preOrder( 2 * index + 1 );
        //���ҵݹ�
        if ((2 * index + 2) < arr.length)
            preOrder( 2 * index + 2 );
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println( "����Ϊ��" );
        }
        //����ݹ�
        if ((2 * index + 1) < arr.length)
            infixOrder( 2 * index + 1 );
        System.out.print( arr[index]+" ");
        //���ҵݹ�
        if ((2 * index + 2) < arr.length)
            infixOrder( 2 * index + 2 );
    }

    public void posterOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println( "����Ϊ��" );
        }
        //����ݹ�
        if ((2 * index + 1) < arr.length)
            posterOrder( 2 * index + 1 );
        //���ҵݹ�
        if ((2 * index + 2) < arr.length)
            posterOrder( 2 * index + 2 );
        System.out.print( arr[index]+" ");
    }

}