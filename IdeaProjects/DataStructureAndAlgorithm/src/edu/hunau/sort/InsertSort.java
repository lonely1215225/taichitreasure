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
			insertValue = nums[i];// ���浱ǰ��Ҫ���뵽��������ֵ
			insertIndex = i - 1;// ��ʾҪ�뵱ǰ��ֵ���бȽϵ�ֵ���±�
			for (int j = insertIndex; j >= 0; j--) {// �ڵ�ǰֵ��ǰ���������б���
				if (nums[insertIndex] > insertValue) {// ����������ֵС��������е�ĳֵ(�Ӻ���ǰ)
					nums[insertIndex + 1] = nums[insertIndex];// ��ô�ͽ�ĳֵ����
					insertIndex--;// ���űȽ��������ĳֵ��ǰһ����ֱ��������еĵ�һ��
				}
			}

			nums[insertIndex + 1] = insertValue;// ��Ϊ�����Ĵ������λ���ڱ��������ڴ�����λ�õ�ǰһ��������Ҫ��������λ��+1
			// System.out.println(Arrays.toString(nums));
		}
	}
}
