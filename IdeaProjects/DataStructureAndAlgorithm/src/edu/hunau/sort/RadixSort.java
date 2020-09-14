package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

	public static void main(String[] args) {
//		int[] arr = { 53, 3, 542, 748, 14, 214 };
//		radixSort(arr);
		int[] nums = new int[8000000];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) (Math.random() * 10000000);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		String beforetime = simpleDateFormat.format(new Date());
		System.out.println("before:" + beforetime);
		radixSort(nums);
		Date date = new Date();
		String afterTime = simpleDateFormat.format(date);
		System.out.println("after:" + afterTime);
		// System.out.println(Arrays.toString(nums));
		//before:2020/02/19 23:37:43:256
		//after:2020/02/19 23:37:43:822
		// 800w数据(数据区间在0——1000w)排序：花了大概不到1s
		//稳定
	}

	public static void radixSort(int[] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i])
				max = arr[i];
		}
		int maxLength = (max + "").length();

		int[][] bucket = new int[10][arr.length];
		int[] bucketElementCount = new int[10];
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

			for (int j = 0; j < arr.length; j++) {
				int index = arr[j] / n % 10;
				bucket[index][bucketElementCount[index]] = arr[j];
				bucketElementCount[index]++;
			}
			int arrIndex = 0;
			for (int k = 0; k < bucketElementCount.length; k++) {
				if (bucketElementCount[k] != 0)
					for (int b = 0; b < bucketElementCount[k]; b++) {
						arr[arrIndex++] = bucket[k][b];
					}
				bucketElementCount[k] = 0;
			}
			// System.out.println("第" + (i + 1) + "次排序为：" + Arrays.toString(arr));
		}
	}
}
