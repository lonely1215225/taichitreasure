package edu.hunau.linkedlist;

public class Josephus {

	public static void main(String[] args) {
		CircleLinkedList circleLinkedList = new CircleLinkedList();
//		circleLinkedList.addBoy(5);
//		circleLinkedList.showBoy();
		circleLinkedList.countBoy(2, 2, 5);
	}

}

class CircleLinkedList {
	private Boy first = null;
	private Boy cur = null;

	/*
	 * start:从第几个小孩开始报数
	 * m:报数累计m人，则第m人出队，然后继续循环
	 * nums:一共nums个小孩
	 * 
	 * 比如有编号为1,2,3,4,5个小孩,start为1,m为2. 则出队顺序为:2,4,1,5,3
	 */
	public void countBoy(int start, int m, int nums) {
		addBoy(nums);

		Boy helper = first;

		while (true) {
			if (helper.getNext() == first)
				break;
			helper = helper.getNext();
		}
		// 从start开始报数
		for (int i = 1; i < start; i++) {//重置头结点至start处
			first = first.getNext();
			helper = helper.getNext();
		}
		while (true) {
			if (helper == first) {
				break;
			}
			System.out.println("出队编号为" + first.getNo());
			first = first.getNext();//重置头结点为出列结点的下一个结点
			helper.setNext(first);//之前first的后一个结点helper不再指向出列的结点，指向头结点
			for (int i = 1; i < m; i++) {//移动头结点到first+m处
				helper = helper.getNext();
				first = first.getNext();
			}
		}
		System.out.println("出队编号为" + first.getNo());

	}

	public void addBoy(int nums) {
		if (nums < 2) {
			System.out.println("人数不得小于2人");
			return;
		}
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			if (i == 1) {
				first = boy;
				first.setNext(first);
				cur = boy;
			}
			cur.setNext(boy);
			boy.setNext(first);
			cur = cur.getNext();
		}
		cur = cur.getNext();
	}

	public void showBoy() {
		if (first == null) {
			System.out.println("没有孩子");
			return;
		}
		while (true) {
			System.out.println("孩子编号为" + cur.getNo());
			cur = cur.getNext();
			if (cur == first) {
				break;
			}
		}

	}
}

class Boy {
	private int no;
	private Boy next;

	public Boy(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
}
