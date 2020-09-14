package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
	public static void main(String[] args) {
		int[] nums = { 2, 7, 4, 9, 1, 4, 3, 2, 0 };
		//insertSort(nums);
//		int[] nums = new int[80000];
//		for (int i = 0; i < nums.length; i++) {
//			nums[i] = (int) (Math.random() * 10000000);
//		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		String beforetime = simpleDateFormat.format(new Date());
		System.out.println("before:" + beforetime);
		insertSort(nums);
		Date date = new Date();
		String afterTime = simpleDateFormat.format(date);
		System.out.println("after:" + afterTime);
	}

	public static void insertSort(int[] nums) {
		int insertValue = 0;
		int insertIndex = 0;
		boolean flag=false;
		for (int i = 1; i < nums.length; i++) {
			insertValue = nums[i];// 保存当前需要插入到有序表的数值
			insertIndex = i - 1;// 表示要与当前数值进行比较的值的下标
			for (int j = insertIndex; j >= 0; j--) {// 在当前值的前面的有序表中遍历
				if (nums[insertIndex] > insertValue) {// 如果待插入的值小于有序表中的某值(从后往前)
					nums[insertIndex + 1] = nums[insertIndex];// 那么就将某值后移
					insertIndex--;// 接着比较有序表中某值的前一个，直到有序表中的第一个
				}
			}

			nums[insertIndex + 1] = insertValue;// 因为真正的待插入的位置在遍历完是在待插入位置的前一个，所以要将待插入位置+1
			// System.out.println(Arrays.toString(nums));
		}
	}
}
