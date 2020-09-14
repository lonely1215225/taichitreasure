package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {
		int[] nums = { 8, 4, 5, 7, 1, 3, 6, 2 };
		int[] temp = new int[nums.length];
//		mergeSort(nums, 0, nums.length - 1, temp);

//		System.out.println(Arrays.toString(nums));
//		int[] nums = new int[8000000];
//		int[] temp = new int[nums.length];
//		for (int i = 0; i < nums.length; i++) {
//			nums[i] = (int) (Math.random() * 10000000);
//		}
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
//		String beforetime = simpleDateFormat.format(new Date());
//		System.out.println("before:" + beforetime);
		mergeSort( nums,0,nums.length-1,temp );
//		Date date = new Date();
//		String afterTime = simpleDateFormat.format(date);
//		System.out.println("after:" + afterTime);
		//800w随机数排序，比较稳定，Collections.sort和Arrays.sort底层也用到了优化的归并排序，
		// 而基本数据类型的排序是用的快排(不稳定没关系,比较多,移动少);
		// 对于对象引用类型是用的归并，比较少，移动多，因为比较比移动慢。
//		before:2020/03/03 15:58:53:580
//		after:2020/03/03 15:58:55:472
//		800w数据排序稳定在2秒 时间复杂度O(nlogn) 占用额外内存
	}

	public static void mergeSort(int[] nums, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right)/ 2;
			mergeSort(nums, left, mid, temp);
			mergeSort(nums, mid + 1, right, temp);
			merge(nums, left, mid, right, temp);
		}
	}

	public static void merge(int[] nums, int left, int mid, int right, int[] temp) {
		int l = left;
		int r = mid + 1;
		int i = 0;

		while (l <= mid && r <= right) {
			if (nums[l] <= nums[r]) {
				temp[i] = nums[l];
				i++;
				l++;
			} else {
				temp[i] = nums[r];
				i++;
				r++;
			}
		}
		while (l <= mid) {
			temp[i] = nums[l];
			i++;
			l++;
		}
		while (r <= right) {
			temp[i] = nums[r];
			i++;
			r++;
		}
		int leftTemp = left;
		i = 0;
		while (leftTemp <= right) {
			nums[leftTemp] = temp[i];
			i++;
			leftTemp++;
			System.out.println(Arrays.toString(nums));
		}
	}
}
