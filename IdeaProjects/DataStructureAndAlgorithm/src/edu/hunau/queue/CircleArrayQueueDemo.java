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
		System.out.println("加入10，20，30,40");
		caq.showQueue();
		System.out.println("出队,此时队里还剩下：");
		caq.getQueue();
		caq.showQueue();
		System.out.println("出队,此时队里还剩下：");
		caq.getQueue();
		caq.showQueue();
		System.out.println("出队,此时队里还剩下：");
		caq.getQueue();
		caq.showQueue();
		System.out.println("加入10，20，30,40");
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
			System.out.println("队已满，不能加入");
			return;
		}
		arr[rear] = data;
		rear = (rear + 1) % maxSize;
	}

	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队为空，不能取数据");
		}
		int tmp = arr[front];
		front = (front+1) % maxSize;
		return tmp;
	}

	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队为空");
		}
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}

	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
}
