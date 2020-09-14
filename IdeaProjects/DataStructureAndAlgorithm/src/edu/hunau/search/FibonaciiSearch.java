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
		while (high >= f[k] - 1)//此处是寻找一个大于等于数组最大索引的一个斐波那契数
			k++;
		int[] temp = Arrays.copyOf(nums, f[k] - 1);//将数组增大到斐波那契数的大小
		for (int i = high + 1; i < temp.length; i++) {//将原数组扩大的部分全部赋值为数组最大值
			temp[i] = nums[high];
		}
		while (low <= high) {
			System.out.println("查找");
			mid = low + f[k - 1] - 1;//黄金比例中心
			System.out.println( "mid="+mid);
			if (key < temp[mid]) {
				high = mid - 1;
				k--;//如果寻找数在mid左边，那么应当k--，前面一个斐波那契数-1为mid
			} else if (key > temp[mid]) {
				low = mid + 1;
				k -= 2;//寻找书在mid右半边，k-=2，后半段的mid=mid+1+f[k-2-1]-1
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
