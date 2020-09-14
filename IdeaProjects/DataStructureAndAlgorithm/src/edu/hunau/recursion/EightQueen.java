package edu.hunau.recursion;

public class EightQueen {
	int max = 8;
	int[] array = new int[max];
	static int count = 0;
	static int compare = 0;

	public static void main(String[] args) {
		EightQueen eightQueen = new EightQueen();
		eightQueen.check(0);
		System.out.println("一个有" + count + "种摆法");
		System.out.println("比较了" + compare + "次");
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
			array[n] = i;// 这里的i代表列，array[n]代表当前皇后放到第i列
			if (judge(n)) {// 判断当前皇后n是否满足条件
				check(n + 1);
			}
		}
	}

	public boolean judge(int n) {
		for (int i = 0; i < n; i++) {// 此处i代表已经摆好的皇后序号，array[i]代表第i个皇后所在的列数, n代表当前的皇后，array[n]代表当前皇后所在的列数
			compare++;
			if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
				return false;
		}
		return true;
	}

}
