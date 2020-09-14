package edu.hunau.search;

import java.util.Arrays;

public class FibonaciiSearch {
	public static int maxSize = 20;

	public static void main(String[] args) {
		int[] nums = { 1,3,5,7,9,11,13,17,19,21 };
		System.out.println("index=" + fiboSearch(nums, 21));
	}

	public static int[] f() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < f.length; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	public static int fiboSearch(int[] nums, int key) {
		int low = 0;
		int high = nums.length - 1;
		int mid = 0;
		int k = 0;
		int[] f = f();
		while (high >= f[k] - 1)//�˴���Ѱ��һ�����ڵ����������������һ��쳲�������
			k++;
		int[] temp = Arrays.copyOf(nums, f[k] - 1);//����������쳲��������Ĵ�С
		for (int i = high + 1; i < temp.length; i++) {//��ԭ��������Ĳ���ȫ����ֵΪ�������ֵ
			temp[i] = nums[high];
		}
		while (low <= high) {
			System.out.println("����");
			mid = low + f[k - 1] - 1;//�ƽ��������
			System.out.println( "mid="+mid);
			if (key < temp[mid]) {
				high = mid - 1;
				k--;//���Ѱ������mid��ߣ���ôӦ��k--��ǰ��һ��쳲�������-1Ϊmid
			} else if (key > temp[mid]) {
				low = mid + 1;
				k -= 2;//Ѱ������mid�Ұ�ߣ�k-=2�����ε�mid=mid+1+f[k-2-1]-1
			} else {
				if (mid <= high)
					return mid;
				else
					return high;
			}
		}
		return -1;
	}
}
