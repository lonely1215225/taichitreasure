package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {
		 int[] nums = { 2, 7, 4, 9, 1, 4, 3, 2, 0 };
		 shellSort(nums);
//		int[] nums = new int[8000000];
//		for (int i = 0; i < nums.length; i++) {
//			nums[i] = (int) (Math.random() * 10000000);
//		}
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
//		String beforetime = simpleDateFormat.format(new Date());
//		System.out.println("before:" + beforetime);
//		shellSort(nums);
//		Date date = new Date();
//		String afterTime = simpleDateFormat.format(date);
//		System.out.println("after:" + afterTime);
//		before:2020/02/13 20:36:38:324
//		after:2020/02/13 20:36:41:82
//800w数据(数据小于1000w)排序：花了大概3s

	}

	public static void shellSort(int[] nums) {
		for (int gap = nums.length / 2; gap > 0; gap /= 2) {// 数组长度减半
			for (int j = gap; j < nums.length; j++) {// 从gap开始遍历到数组尾部
				//int j = i;// 当前索引复制给j为从当前i到之前所有的j-gap
				int temp = nums[j];// 保存当前待插入的值
				while (j - gap >= 0 && temp < nums[j - gap]) {// 同一间隔组从后往前进行比较，并且当索引值j-gap<0时就表示这一组已排序完成
					nums[j] = nums[j - gap];// 元素后移
					j -= gap;// 进行下一个组内比较，j等于组内前一个元素的索引
				}
				nums[j] = temp;// 将待插入的值放到
				System.out.println(Arrays.toString(nums));
			}
		}
	}
}
