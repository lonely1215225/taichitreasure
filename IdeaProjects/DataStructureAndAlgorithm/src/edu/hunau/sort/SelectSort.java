package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {
//		int[] nums = { 2, 9, 1, 3, 6, 7, 5, 0 };
//		selectSort(nums);
		int[] nums = new int[80000];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) (Math.random() * 10000000);
		}
		long beforeTime = System.currentTimeMillis();
		selectSort(nums);
		long afterTime = System.currentTimeMillis();
		System.out.println("after-before=" + (afterTime-beforeTime)+"ms");
//		before:2020/02/12 16:22:59:872
//		after:2020/02/12 16:23:00:915
	}

	public static void selectSort(int[] nums) {

		for (int i = 0; i < nums.length - 1; i++) {
			int min = nums[i];
			int index = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < min) {
					min = nums[j];
					index = j;
				}
			}
			if (index != i) {
				nums[index] = nums[i];
				nums[i] = min;
			}
			// System.out.println(Arrays.toString(nums));
		}
	}
}
