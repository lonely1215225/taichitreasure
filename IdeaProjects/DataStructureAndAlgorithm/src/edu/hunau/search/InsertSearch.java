package edu.hunau.search;

import java.util.ArrayList;
import java.util.List;

public class InsertSearch {
	/*
	 * 插值法查找，自适应查找 在关键字均匀情况下，要比二分查找效率高 但是在不均匀关键字下，有时候不如二分查找
	 */
	public static void main(String[] args) {
		int[] nums = { 1, 1, 3, 5, 6, 9, 45, 67, 88, 99 };
		List<Integer> insertSearch = insertSearch(nums, 0, nums.length - 1, 1);
		System.out.println(insertSearch);

	}

	public static List<Integer> insertSearch(int[] nums, int left, int right, int findValue) {
		System.out.println("查询");
		List<Integer> list = new ArrayList<>();
		if (left > right)
			return null;
		int mid = left + (findValue - nums[left]) * (right - left) / (nums[right] - nums[left]);
		System.out.println("mid=" + mid);
		if (findValue < nums[mid])
			return insertSearch(nums, left, mid - 1, findValue);
		else if (findValue > nums[mid])
			return insertSearch(nums, mid + 1, right, findValue);
		else {
			int temp = mid - 1;
			while (temp >= 0 && nums[temp] == findValue) {
				list.add(temp);
				temp--;
			}
			list.add(mid);
			temp = mid + 1;
			while (temp < nums.length && nums[temp] == findValue) {
				list.add(temp);
				temp++;
			}
			return list;
		}
	}
}
