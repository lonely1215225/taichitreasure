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
	 * start:�ӵڼ���С����ʼ����
	 * m:�����ۼ�m�ˣ����m�˳��ӣ�Ȼ�����ѭ��
	 * nums:һ��nums��С��
	 * 
	 * �����б��Ϊ1,2,3,4,5��С��,startΪ1,mΪ2. �����˳��Ϊ:2,4,1,5,3
	 */
	public void countBoy(int start, int m, int nums) {
		addBoy(nums);

		Boy helper = first;

		while (true) {
			if (helper.getNext() == first)
				break;
			helper = helper.getNext();
		}
		// ��start��ʼ����
		for (int i = 1; i < start; i++) {//����ͷ�����start��
			first = first.getNext();
			helper = helper.getNext();
		}
		while (true) {
			if (helper == first) {
				break;
			}
			System.out.println("���ӱ��Ϊ" + first.getNo());
			first = first.getNext();//����ͷ���Ϊ���н�����һ�����
			helper.setNext(first);//֮ǰfirst�ĺ�һ�����helper����ָ����еĽ�㣬ָ��ͷ���
			for (int i = 1; i < m; i++) {//�ƶ�ͷ��㵽first+m��
				helper = helper.getNext();
				first = first.getNext();
			}
		}
		System.out.println("���ӱ��Ϊ" + first.getNo());

	}

	public void addBoy(int nums) {
		if (nums < 2) {
			System.out.println("��������С��2��");
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
			System.out.println("û�к���");
			return;
		}
		while (true) {
			System.out.println("���ӱ��Ϊ" + cur.getNo());
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
