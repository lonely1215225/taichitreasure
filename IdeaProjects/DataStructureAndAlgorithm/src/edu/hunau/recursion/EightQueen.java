package edu.hunau.recursion;

public class EightQueen {
	int max = 8;
	int[] array = new int[max];
	static int count = 0;
	static int compare = 0;

	public static void main(String[] args) {
		EightQueen eightQueen = new EightQueen();
		eightQueen.check(0);
		System.out.println("һ����" + count + "�ְڷ�");
		System.out.println("�Ƚ���" + compare + "��");
	}

	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		count++;
	}

	public void check(int n) {
		if (n == max) {
			print();
			return;
		}
		for (int i = 0; i < max; i++) {
			array[n] = i;// �����i�����У�array[n]����ǰ�ʺ�ŵ���i��
			if (judge(n)) {// �жϵ�ǰ�ʺ�n�Ƿ���������
				check(n + 1);
			}
		}
	}

	public boolean judge(int n) {
		for (int i = 0; i < n; i++) {// �˴�i�����Ѿ��ںõĻʺ���ţ�array[i]�����i���ʺ����ڵ�����, n����ǰ�Ļʺ�array[n]����ǰ�ʺ����ڵ�����
			compare++;
			if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
				return false;
		}
		return true;
	}

}
