package edu.hunau.queue;

public class CircleArrayQueueDemo {
	private int maxSize;
	private int front;
	private int rear;
	private int[] arr;

	public static void main(String[] args) {
		CircleArrayQueueDemo caq = new CircleArrayQueueDemo(4);
		caq.addQueue(10);
		caq.addQueue(20);
		caq.addQueue(30);
		caq.addQueue(40);
		System.out.println("����10��20��30,40");
		caq.showQueue();
		System.out.println("����,��ʱ���ﻹʣ�£�");
		caq.getQueue();
		caq.showQueue();
		System.out.println("����,��ʱ���ﻹʣ�£�");
		caq.getQueue();
		caq.showQueue();
		System.out.println("����,��ʱ���ﻹʣ�£�");
		caq.getQueue();
		caq.showQueue();
		System.out.println("����10��20��30,40");
		caq.addQueue(10);
		caq.addQueue(20);
		caq.addQueue(30);
		caq.addQueue(40);
		caq.showQueue();
	}

	public CircleArrayQueueDemo(int maxSize) {
		this.maxSize = maxSize+1;
		arr = new int[maxSize+1];
	}

	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}

	public boolean isEmpty() {
		return rear == front;
	}

	public void addQueue(int data) {
		if (isFull()) {
			System.out.println("�����������ܼ���");
			return;
		}
		arr[rear] = data;
		rear = (rear + 1) % maxSize;
	}

	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("��Ϊ�գ�����ȡ����");
		}
		int tmp = arr[front];
		front = (front+1) % maxSize;
		return tmp;
	}

	public void showQueue() {
		if (isEmpty()) {
			System.out.println("��Ϊ��");
		}
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
}
