package edu.hunau.linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(8, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(10, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(11, "林冲", "豹子头");
		HeroNode2 hero5 = new HeroNode2(2, "鲁智深", "花和尚");
		HeroNode2 hero6 = new HeroNode2(3, "孙悟空", "齐天大圣");
		HeroNode2 hero7 = new HeroNode2(7, "林黛玉", "贾宝玉");

		DoubleLinkedList dl = new DoubleLinkedList();

		dl.addByOrder(hero1);
		dl.addByOrder(hero4);
		dl.addByOrder(hero3);
		dl.addByOrder(hero2);
		dl.addByOrder(hero5);
		dl.addByOrder(hero6);
		dl.addByOrder(hero7);

		dl.showList();
	}

}

class DoubleLinkedList {
	HeroNode2 head = new HeroNode2(0, null, null);

	public void addByOrder(HeroNode2 heroNode) {
		HeroNode2 temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null)
				break;
			if (heroNode.no <= temp.next.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			heroNode.next = temp.next;
		}
		temp.next = heroNode;
		heroNode.pre = temp;
	}

	public void showList() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode2 temp = head;
		while (true) {
			if (temp.next == null)
				break;
			temp=temp.next;
			System.out.println(temp);
		}
	}
}

class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;
	public HeroNode2 pre;

	public HeroNode2(int hNo, String hName, String hNickname) {
		this.no = hNo;
		this.name = hName;
		this.nickname = hNickname;
	}

	@Override
	public String toString() {
		return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}