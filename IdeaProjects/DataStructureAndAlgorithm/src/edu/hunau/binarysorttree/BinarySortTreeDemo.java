package edu.hunau.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7, 3, 10, 1, 5, 9, 12,9,9};
        BinarySort binarySort=new BinarySort();
        for (int val : arr) {
            binarySort.add( new Node( val ) );
        }
        binarySort.infixOrder();
        //System.out.println( "12的父节点为：" + binarySort.searchParent( 12 ) );
        System.out.println( "===============================" );
        binarySort.delNode( 5 );
        binarySort.delNode( 9 );
        binarySort.delNode( 9 );
        binarySort.delNode( 9 );
        binarySort.delNode( 12 );
        binarySort.delNode( 7 );
        binarySort.delNode( 3 );
        binarySort.delNode( 1 );
        binarySort.delNode( 10 );
        binarySort.infixOrder();
        System.out.println( "根节点：" + binarySort.root );
    }
}

class BinarySort {
    Node root;

    public void add(Node node) {
        if (root == null)
            root=node;
        else
            root.add( node );
    }

    public void infixOrder( ) {
        if (root == null)
            return;
        else
            root.infixOrder();
    }

    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent( value );
    }

    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search( value );
    }

    public int delRightTreeMin(Node node) {
        Node target=node;
        while (target.left != null)
            target=target.left;
        delNode( target.value );
        return target.value;
    }

    public void delNode(int value) {
        if (root == null)
            return;
        else {
            Node targetNode=search( value );
            if (targetNode == null)
                return;
            if (root.left == null && root.right == null) {
                root=null;
                return;
            }
            Node parent=searchParent( value );
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left=null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right=null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal=delRightTreeMin( targetNode.right );
                targetNode.value=minVal;
            } else {//只有一颗子树的结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left=targetNode.left;
                        } else {
                            parent.right=targetNode.left;
                        }
                    } else root=targetNode.left;
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left=targetNode.right;
                        } else
                            parent.right=targetNode.right;
                    } else
                        root=targetNode.right;
                }
            }
        }

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value=value;
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
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value))
            return this;
        else
            if (value<this.value&&this.left!=null)
                return this.left.searchParent( value );
            else if (value>this.value&&this.right!=null)
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