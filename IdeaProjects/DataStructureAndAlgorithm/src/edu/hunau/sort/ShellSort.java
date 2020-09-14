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
//800w����(����С��1000w)���򣺻��˴��3s

	}

	public static void shellSort(int[] nums) {
		for (int gap = nums.length / 2; gap > 0; gap /= 2) {// ���鳤�ȼ���
			for (int j = gap; j < nums.length; j++) {// ��gap��ʼ����������β��
				//int j = i;// ��ǰ�������Ƹ�jΪ�ӵ�ǰi��֮ǰ���е�j-gap
				int temp = nums[j];// ���浱ǰ�������ֵ
				while (j - gap >= 0 && temp < nums[j - gap]) {// ͬһ�����Ӻ���ǰ���бȽϣ����ҵ�����ֵj-gap<0ʱ�ͱ�ʾ��һ�����������
					nums[j] = nums[j - gap];// Ԫ�غ���
					j -= gap;// ������һ�����ڱȽϣ�j��������ǰһ��Ԫ�ص�����
				}
				nums[j] = temp;// ���������ֵ�ŵ�
				System.out.println(Arrays.toString(nums));
			}
		}
	}
}
