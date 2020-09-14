package Algorithm.leetcode.training;

import java.util.Arrays;

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 7, 11};
        int[] nums2 = {3, 4, 6, 9, 10};
        double v = findMedianSortedArrays(nums1, nums2);
        System.out.println(v);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArray=null;
        if (nums1==null&&nums2==null) return 0;
        if (nums1 != null && nums2 != null)
            mergedArray = mergedArray(nums1, nums2);
        else if (nums1==null||nums2==null) mergedArray=nums1==null?nums2:nums1;
        int length = mergedArray.length;
        int index = length % 2 == 0 ? length >> 1 : (length >> 1) + 1;
        if (length == index << 1) {
            return (mergedArray[index - 1] + mergedArray[index]) / 2d;
        } else {
            return mergedArray[index - 1];
        }
    }

    public static int[] mergedArray(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (true) {
            if (i > nums1.length - 1 && j > nums2.length - 1) break;
            if (i > nums1.length - 1 && j <= nums2.length - 1) {
                merged[k] = nums2[j];
                j++;
            }
            if (i <= nums1.length - 1 && j > nums2.length - 1) {
                merged[k] = nums1[i];
                i++;
            }
            if (i <= nums1.length - 1 && j <= nums2.length - 1) {
                if (nums1[i] < nums2[j]) {
                    merged[k] = nums1[i];
                    i++;
                } else {
                    merged[k] = nums2[j];
                    j++;
                }
            }
            k++;
        }
        return merged;
    }
}
