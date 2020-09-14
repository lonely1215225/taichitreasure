package edu.hunau.recursion;

public class Labyrinth {

	public static void main(String[] args) {
		int[][] array = new int[8][8];
		for (int j[] : getMaze(array)) {
			for (int i : j)
				System.out.printf("%d  ", i);
			System.out.println();
		}

		setWay(array, 1, 1);
		System.out.println("------------------------------------------");
		for (int j[] : getMaze(array)) {
			for (int i : j)
				System.out.printf("%d  ", i);
			System.out.println();
		}
	}

	public static int[][] getMaze(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			array[0][i] = 1;
			array[array.length - 1][i] = 1;
			array[i][0] = 1;
			array[i][array[i].length - 1] = 1;
		}
		array[3][1] = 1;
		array[3][3] = 1;
		array[2][3] = 1;
		array[6][4] = 1;
		array[5][4] = 1;
		array[5][5] = 1;
		array[4][5] = 1;
		array[3][5] = 1;
		array[2][5] = 1;
		//array[6][6] = 1;

		return array;
	}

	// 下右上左
	public static boolean setWay(int[][] array, int i, int j) {
		if (array[6][6] == 7)
			return true;
		else {
			if (array[i][j] == 0) {
				array[i][j] = 7;
				if (setWay(array, i + 1, j))
					return true;
				else if (setWay(array, i, j + 1))
					return true;
				else if (setWay(array, i - 1, j))
					return true;
				else if (setWay(array, i, j - 1))
					return true;
				else {
					array[i][j] = 3;
					return false;
				}
			} else
				return false;
		}
	}

	// 右下左上
	public static boolean setWay2(int[][] array, int i, int j) {
		if (array[6][6] == 7)
			return true;
		else {
			if (array[i][j] == 0) {
				array[i][j] = 7;
				if (setWay2(array, i, j + 1))
					return true;
				else if (setWay2(array, i + 1, j))
					return true;
				else if (setWay2(array, i, j - 1))
					return true;
				else if (setWay2(array, i - 1, j))
					return true;
				else {
					array[i][j] = 3;
					return false;
				}
			} else
				return false;
		}
	}

}
