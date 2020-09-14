package edu.hunau.linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(8, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(10, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(11, "林冲", "豹子头");
		HeroNode hero5 = new HeroNode(2, "鲁智深", "花和尚");
		HeroNode hero6 = new HeroNode(3, "孙悟空", "齐天大圣");
		HeroNode hero7 = new HeroNode(7, "林黛玉", "贾宝玉");

		SingleLinkedList sl = new SingleLinkedList();
		SingleLinkedList sl2 = new SingleLinkedList();
//		sl.add(hero1);
//		sl.add(hero4);
//		sl.add(hero3);
//		sl.add(hero2);

		sl.addByOrder(hero1);
		sl.addByOrder(hero4);
		sl.addByOrder(hero3);
		sl.addByOrder(hero2);
		// sl.addFirst(hero2);
		sl2.addByOrder(hero5);
		sl2.addByOrder(hero6);
		sl2.addByOrder(hero7);

		System.out.println("=========添加数据==================");

		sl.list();

		System.out.println("============改写name==============");
		sl.update(hero3, "张卓悦");
		sl.list();

		System.out.println("============删除4号和1号==============");
		sl.delete(hero4);
		sl.delete(hero1);
//		
//		
//		
//		SingleLinkedList merge = new SingleLinkedList();
//		SingleLinkedList m = merge.merge(sl, sl2);
//		m.list();
		// sl.revList();
		sl.list();
	}

}

class SingleLinkedList {
	private HeroNode head = new HeroNode(0, "", "");

	//合并两个单链表并有序化
	public SingleLinkedList merge(SingleLinkedList sl, SingleLinkedList sl2) {
		SingleLinkedList ss = new SingleLinkedList();
		HeroNode temp1 = sl.head.next;
		HeroNode temp2 = sl2.head.next;
		HeroNode next = null;
		while (true) {
			if (temp1 == null && temp2 == null) {
				break;
			}
			if (temp1 == null && temp2 != null) {
				next=temp2.next;
				ss.addByOrder(temp2);
				temp2 = next;
			} else if (temp2 == null && temp1 != null) {
				next=temp1.next;
				ss.addByOrder(temp1);
				temp1 = next;
			}
			if (temp1 != null && temp2 != null) {
				if (temp1.no < temp2.no) {//把no小的加入新链表
					next = temp1.next;//保存下一节点
					ss.addByOrder(temp1);//这一步会导致temp1指向改变，导致链表丢失
					temp1 = next;//所以需要恢复到temp1得下一个
				} else {
					next = temp2.next;
					ss.addByOrder(temp2);
					temp2 = next;
				}
			}
		}
		return ss;
	}

//	public int getMinNo() {
//		HeroNode temp=head;
//		int min=999;
//		while(true) {
//			if(temp.next==null)
//				break;
//			if (temp.no <= temp.next.no) {
//				min = temp.no;
//			}
//			temp = temp.next;
//		}
//		return min;
//	}
//
//	public int getMaxNo() {
//		HeroNode temp = head;
//		int max = 0;
//		while (true) {
//			if (temp.next == null)
//				break;
//			if (temp.no <= temp.next.no) {
//				max = temp.next.no;
//			}
//			temp = temp.next;
//		}
//		return max;
//	}

	public int getLength() {
		HeroNode temp = head;
		int length = 0;
		while (temp.next != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}

	public HeroNode getEndTale() {
		HeroNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		return temp;
	}

	// 单链表反转
	public void revList() {
		// 关键就是借助了下面这个辅助头节点
		HeroNode reverseNode = new HeroNode(0, "", "");
		// 下面这个是要保存当前节点的下一个节点的
		HeroNode next = null;
		HeroNode temp = head.next;// 将head后的一大串单链表copy一份交给temp管理
		while (true) {
			if (temp == null) {
				break;// 单链表为空
			}
			next = temp.next;// 保存下一个节点，比如当前temp->1,那么next就等于temp->2
			temp.next = reverseNode.next;// 1的next赋值为null，下一次就是2的next赋值为1
			reverseNode.next = temp;// 这一步就是上面“下一次里的next”赋值为1的操作
			temp = next;// 到这一步的时候，本次temp已经和原来的单链表断开了，所以需要next提前保存temp.next
		}
		head.next = reverseNode.next;
	}

	// 头插法
	public void addFirst(HeroNode heroNode) {
		HeroNode temp = head;
		heroNode.next = temp.next;
		temp.next = heroNode;
	}

	// 尾插法
	public void addEnd(HeroNode heroNode) {
		// 尾插法需要遍历单链表到最后一个节点的下一个节点为null的上一个节点
		HeroNode temp = head;
		while (true) {
			if (temp.next == null)
				break;
			else
				temp = temp.next;
		}
		temp.next = heroNode;
	}

//尾插法和普通插入单链表一毛一样
	public void add(HeroNode heroNode) {
		HeroNode temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
	}

	public void addByOrder(HeroNode heroNode) {
		HeroNode temp = head;
		boolean flag = false;
		while (true) {
			if (temp.next == null)
				break;
			if (temp.next.no > heroNode.no)
				break;
			if (temp.next.no == heroNode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (!flag) {
			heroNode.next = temp.next;
			temp.next = heroNode;
		} else {
			System.out.println("存在" + heroNode.no);
		}
	}

	public void delete(HeroNode heroNode) {
		HeroNode temp =head;
		//boolean flag = false;
		while (true) {
			if (temp.next == null) {
				System.out.println("链表为空");
				break;
			}
			if (temp.next == heroNode) {
				//flag = true;
				temp.next = heroNode.next;
				heroNode.next = null;
				break;
			}
			temp = temp.next;
		}
//		if (flag) {
//			temp.next = heroNode.next;
//			heroNode.next = null;
//		} else {
//			System.out.println("链表为空");
//		}
	}

	public void list() {
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		while (true) {
			if (temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}

	public void update(HeroNode heroNode, String name) {
		HeroNode temp = head;
	//	boolean flag = false;
		while (true) {
			if (temp.next == null) {
				System.out.println("链表为空");
				break;
			}
			if (temp.next == heroNode) {
			//	flag = true;
				heroNode.name = name;
				break;
			}
			temp = temp.next;
		}
//		if (flag)
//			heroNode.name = name;
//		else
//			System.out.println("链表为空");
	}
}

class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;

	public HeroNode(int hNo, String hName, String hNickname) {
		this.no = hNo;
		this.name = hName;
		this.nickname = hNickname;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}
