package edu.hunau.stack;

public class ArrayStackDemo {
	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack(5);
		arrayStack.push(10);
		arrayStack.push(20);
		arrayStack.push(30);
		arrayStack.push(40);
		System.out.println("³öÕ»Îª" + arrayStack.pop());
		System.out.println("³öÕ»Îª" + arrayStack.pop());
		System.out.println("³öÕ»Îª" + arrayStack.pop());
		System.out.println("Õ»¶¥Îª" + arrayStack.peek());
		arrayStack.showStack();
	}
}

class ArrayStack {
	private int maxSize;
	private int[] stack;
	private int top = -1;

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == maxSize - 1;
	}

	public void push(int data) {
		if (isFull()) {
			System.out.println("Õ»Âú");
			return;
		}
		top++;
		stack[top] = data;
	}

	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("Õ»¿Õ");
		}
		int data = stack[top];
		top--;
		return data;
	}

	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("Õ»¿Õ");
		}
		return stack[top];
	}

	public void showStack() {
		if (isEmpty()) {
			throw new RuntimeException("Õ»¿Õ");
		}
		while (true) {
			if (top == -1)
				break;
			System.out.println(stack[top]);
			top--;
		}
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int[] getStack() {
		return stack;
	}

	public void setStack(int[] stack) {
		this.stack = stack;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public boolean isOper(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	public int priority(int oper) {
		if (oper == '+' || oper == '-')
			return 1;
		else if (oper == '*' || oper == '/')
			return 2;
		else
			return -1;
	}

	public int calc(int num1, int num2, int oper) {
		switch (oper) {
		case '+':
			return num1 + num2;
		case '-':
			return num2 - num1;
		case '*':
			return num1 * num2;
		case '/':
			return num2 / num1;
		default:
			break;
		}
		return 0;
	}
}
