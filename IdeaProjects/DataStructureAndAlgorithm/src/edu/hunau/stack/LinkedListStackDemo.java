package edu.hunau.stack;

public class LinkedListStackDemo {

	public static void main(String[] args) {
		LinkedListStack linkedListStack = new LinkedListStack();
		linkedListStack.push(10);
		linkedListStack.push(20);
		linkedListStack.push(30);
		linkedListStack.push(40);
		System.out.println("出栈为" + linkedListStack.pop());
		System.out.println("出栈为" + linkedListStack.pop());
		System.out.println("此时栈顶为" + linkedListStack.peek());		
		linkedListStack.showStack();
	}

}

class LinkedListStack {
	Node head = new Node(0);
	Node temp = null;

	public boolean isEmpty() {
		return head.getNext() == null;
	}

	public void push(int data) {

		Node node = new Node(data);
		node.setNext(head.getNext());
		head.setNext(node);
		temp = node;
	}

	public int pop() {
		if (isEmpty() || temp.getNext() == null) {
			throw new RuntimeException("栈空");
		}
		int top = head.getNext().getNo();
		head.setNext(temp.getNext());
		temp = temp.getNext();
		return top;
	}

	public int peek() {
		return head.getNext().getNo();
	}
	public void showStack() {

		while (true) {
			System.out.println(temp.getNo());
			if (temp.getNext() == null) {
				break;
			}
			temp = temp.getNext();
		}
	}
}

class Node {
	private Node next;
	private int no;

	public Node(int no) {
		this.no = no;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}