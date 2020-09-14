package Algorithm.leetcode.training;

import java.util.Arrays;

public class TwoArraysMidNum {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 6};
        int[] nums2 = {3, 4, 7};
        int[] mergeNums = new int[nums1.length + nums2.length];
        int index1 = 0;
        int index2 = 0;
        int index=0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                mergeNums[index] = nums1[index1];
                index1++;
            } else {
                mergeNums[index] = nums2[index2];
                index2++;
            }
            index++;
        }
        while (index1 < nums1.length) {
            mergeNums[index] = nums1[index1];
            index1++;
            index++;
        }
        while (index2 < nums2.length) {
            mergeNums[index] = nums2[index2];
            index2++;
            index++;
        }
        System.out.println(Arrays.toString(mergeNums));
        int mLength=mergeNums.length;
        int midIndex=mLength>>1;
        double mid;
        if ((mLength&1)==0)
            mid=(double)(mergeNums[midIndex]+mergeNums[midIndex-1])/2;
        else
            mid=mergeNums[mLength>>1];
        System.out.println(mid);
    }
}
