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
		// 800w数据(数据区间在0——1000w)排序：花了大概不到2s

	}

	public static void quickSort(int[] nums, int left, int right) {
		int pivot = nums[(left + right) / 2];//基准找的中间的
		int l = left;//基准左边的
		int r = right;//基准右边
		int temp = 0;//用于交换的临时变量
		while (l < r) {//当索引下标基准左边的小于右边的，说明基准两边的值需要排序
			while (nums[l] < pivot)//基准左边的值小于基准时，则不用交换，继续比较下一个，当基准左边的某个值大于基准时，跳出循环，此时l为需要交换的元素下标
				l++;
			while (nums[r] > pivot)//同理，同上
				r--;
			if (l == r)//如果当基准左右索引相等时，则退出当前循环，主要是(为了避免同一索引位置的交换)。
				break;
			temp = nums[l];
			nums[l] = nums[r];
			nums[r] = temp;
			if (nums[l] == pivot)//为了避免重复执行上面两个while语句，(当只有两个元素进行比较交换后，那么就应该退出本次循环)
				r--;
			if (nums[r] == pivot)//同理，同上
				l++;
			 //System.out.println(Arrays.toString(nums));
		}
		r--;//上面的循环执行结束后，基准左小右大成立
		l++;//必然l==r==pivot的下标，所以进行r--和l++是为了给左右递归做准备！
		if (left < r)//当基准左边第一个r的索引大于0时，那么说明基准左边起码有≥2个值，需要再进行排序
			quickSort(nums, left, r);//左递归
		if (l < right)//同理，同上
			quickSort(nums, l, right);//有递归
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
