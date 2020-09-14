package edu.hunau.linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1, "�ν�", "��ʱ��");
		HeroNode hero2 = new HeroNode(8, "¬����", "������");
		HeroNode hero3 = new HeroNode(10, "����", "�Ƕ���");
		HeroNode hero4 = new HeroNode(11, "�ֳ�", "����ͷ");
		HeroNode hero5 = new HeroNode(2, "³����", "������");
		HeroNode hero6 = new HeroNode(3, "�����", "�����ʥ");
		HeroNode hero7 = new HeroNode(7, "������", "�ֱ���");

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

		System.out.println("=========�������==================");

		sl.list();

		System.out.println("============��дname==============");
		sl.update(hero3, "��׿��");
		sl.list();

		System.out.println("============ɾ��4�ź�1��==============");
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

	//�ϲ���������������
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
				if (temp1.no < temp2.no) {//��noС�ļ���������
					next = temp1.next;//������һ�ڵ�
					ss.addByOrder(temp1);//��һ���ᵼ��temp1ָ��ı䣬��������ʧ
					temp1 = next;//������Ҫ�ָ���temp1����һ��
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

	// ������ת
	public void revList() {
		// �ؼ����ǽ����������������ͷ�ڵ�
		HeroNode reverseNode = new HeroNode(0, "", "");
		// ���������Ҫ���浱ǰ�ڵ����һ���ڵ��
		HeroNode next = null;
		HeroNode temp = head.next;// ��head���һ�󴮵�����copyһ�ݽ���temp����
		while (true) {
			if (temp == null) {
				break;// ������Ϊ��
			}
			next = temp.next;// ������һ���ڵ㣬���統ǰtemp->1,��ônext�͵���temp->2
			temp.next = reverseNode.next;// 1��next��ֵΪnull����һ�ξ���2��next��ֵΪ1
			reverseNode.next = temp;// ��һ���������桰��һ�����next����ֵΪ1�Ĳ���
			temp = next;// ����һ����ʱ�򣬱���temp�Ѿ���ԭ���ĵ�����Ͽ��ˣ�������Ҫnext��ǰ����temp.next
		}
		head.next = reverseNode.next;
	}

	// ͷ�巨
	public void addFirst(HeroNode heroNode) {
		HeroNode temp = head;
		heroNode.next = temp.next;
		temp.next = heroNode;
	}

	// β�巨
	public void addEnd(HeroNode heroNode) {
		// β�巨��Ҫ�������������һ���ڵ����һ���ڵ�Ϊnull����һ���ڵ�
		HeroNode temp = head;
		while (true) {
			if (temp.next == null)
				break;
			else
				temp = temp.next;
		}
		temp.next = heroNode;
	}

//β�巨����ͨ���뵥����һëһ��
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
			System.out.println("����" + heroNode.no);
		}
	}

	public void delete(HeroNode heroNode) {
		HeroNode temp =head;
		//boolean flag = false;
		while (true) {
			if (temp.next == null) {
				System.out.println("����Ϊ��");
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
//			System.out.println("����Ϊ��");
//		}
	}

	public void list() {
		if (head.next == null) {
			System.out.println("����Ϊ��");
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
				System.out.println("����Ϊ��");
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
//			System.out.println("����Ϊ��");
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
