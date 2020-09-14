package edu.hunau.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
/*
 * 二分查找
 */
	public static void main(String[] args) {
		int[] nums = { 1, 1, 3, 5, 6, 9, 45, 67, 88, 99 };

//		int[] nums = { 599, 699, 899,999, 1000, 1111, 1111, 1222,1333,1444 };
		List<Integer> search = binarySearch(nums, 0, nums.length-1, 1);
		System.out.println(search);
	}

	public static List<Integer> binarySearch(int[] nums, int left, int right, int findValue) {
		System.out.println("查询");
		int mid = (left + right) >> 1;
		int midValue = nums[mid];
		List<Integer> list = new ArrayList<>();
		if (left > right)
			return null;
		if (findValue < midValue)
			return binarySearch(nums, left, mid - 1, findValue);
		else if (findValue > midValue)
			return binarySearch(nums, mid + 1, right, findValue);
		else {
			int temp = mid - 1;
			while (temp >= 0 && nums[temp] == midValue) {
				list.add(temp);
				temp--;
			}
			list.add(mid);
			temp = mid + 1;
			while (temp <= nums.length && nums[temp] == midValue) {
				list.add(temp);
				temp++;
			}
			return list;
		}
		
	}
}
