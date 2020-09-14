package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {
//		int[] nums = { 2, 7, 4, 9, 1, 4, 3, 2, 0 };
		int[] nums = new int[80000];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) (Math.random() * 10000000);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		String beforetime = simpleDateFormat.format(new Date());
		System.out.println("before:" + beforetime);
		bubbleSort(nums);
		Date date = new Date();
		String afterTime = simpleDateFormat.format(date);
		System.out.println("after:" + afterTime);
		//System.out.println(Arrays.toString(nums));
//		before:2020/02/12 16:23:20:703
//		after:2020/02/12 16:23:32:750
//		�Ƚ��˲�������:1603714390
	}

	public static void bubbleSort(int[] nums) {
		int temp = 0;
		boolean flag = false;
		int len=nums.length-1;
		int lastSwapLoc=len;
		for (int j = 0; j < nums.length - 1; j++) {
			for (int i = 0; i < len; i++) {// �˴�-i�ǽ������ֵȷ�����������ٱȽ����ǣ�������ѭ��

				if (nums[i] > nums[i + 1]) {
					flag = true;
					temp = nums[i];
					nums[i] = nums[i + 1];
					nums[i + 1] = temp;
					lastSwapLoc=i;
				}
			}
			len=lastSwapLoc;
			if (!flag)// �Ż�: ˵������û�н��бȽϣ������j���Ѿ��ź����״̬�£��ּ�������.
				return;
			else
				flag = false;
			 //System.out.println(Arrays.toString(nums));
		}
	}

}
