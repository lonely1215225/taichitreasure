package edu.hunau.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
	public static void main(String[] args) {
		int[] nums={-9,78,0,23,-56,70};
//		int[] nums = new int[8000000];
//		for (int i = 0; i < nums.length; i++) {
//			nums[i] = (int) (Math.random() * 10000000);
//		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		String beforetime = simpleDateFormat.format(new Date());
		System.out.println("before:" + beforetime);
		//quickSort(nums, 0, nums.length - 1);
		sort(nums,0,nums.length-1);
		Date date = new Date();
		String afterTime = simpleDateFormat.format(date);
		System.out.println("after:" + afterTime);
		 System.out.println(Arrays.toString(nums));
//		before:2020/02/13 20:39:07:910
//		after:2020/02/13 20:39:09:310
		// 800w����(����������0����1000w)���򣺻��˴�Ų���2s

	}

	public static void quickSort(int[] nums, int left, int right) {
		int pivot = nums[(left + right) / 2];//��׼�ҵ��м��
		int l = left;//��׼��ߵ�
		int r = right;//��׼�ұ�
		int temp = 0;//���ڽ�������ʱ����
		while (l < r) {//�������±��׼��ߵ�С���ұߵģ�˵����׼���ߵ�ֵ��Ҫ����
			while (nums[l] < pivot)//��׼��ߵ�ֵС�ڻ�׼ʱ�����ý����������Ƚ���һ��������׼��ߵ�ĳ��ֵ���ڻ�׼ʱ������ѭ������ʱlΪ��Ҫ������Ԫ���±�
				l++;
			while (nums[r] > pivot)//ͬ��ͬ��
				r--;
			if (l == r)//�������׼�����������ʱ�����˳���ǰѭ������Ҫ��(Ϊ�˱���ͬһ����λ�õĽ���)��
				break;
			temp = nums[l];
			nums[l] = nums[r];
			nums[r] = temp;
			if (nums[l] == pivot)//Ϊ�˱����ظ�ִ����������while��䣬(��ֻ������Ԫ�ؽ��бȽϽ�������ô��Ӧ���˳�����ѭ��)
				r--;
			if (nums[r] == pivot)//ͬ��ͬ��
				l++;
			 //System.out.println(Arrays.toString(nums));
		}
		r--;//�����ѭ��ִ�н����󣬻�׼��С�Ҵ����
		l++;//��Ȼl==r==pivot���±꣬���Խ���r--��l++��Ϊ�˸����ҵݹ���׼����
		if (left < r)//����׼��ߵ�һ��r����������0ʱ����ô˵����׼��������С�2��ֵ����Ҫ�ٽ�������
			quickSort(nums, left, r);//��ݹ�
		if (l < right)//ͬ��ͬ��
			quickSort(nums, l, right);//�еݹ�
	}

	public static void sort(int[] nums,int l,int r){
		if (r<=l)return;
		int lt=l,i=l+1,gt=r;
		int pivot=nums[l];
		while(i<=gt){
			int cmp=compare(nums[i],pivot);
			if(cmp<0) exch(nums,lt++,i++);
			else if(cmp>0) exch(nums,i,gt--);
			else i++;
		}
		sort(nums,l,lt-1);
		sort(nums,gt+1,r);
	}
	private static void exch(int[] nums,int i,int j){
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	private static int compare(int a,int b){
		if(a<b)return -1;
		else if(a>b) return 1;
		else return 0;
	}

}
