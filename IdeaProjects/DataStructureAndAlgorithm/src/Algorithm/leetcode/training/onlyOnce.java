package Algorithm.leetcode.training;

import java.util.HashSet;

public class onlyOnce {
    public static void main(String[] args) {
        int[] nums={43,16,45,89,45,-2147483648,45,2147483646,-2147483647,-2147483648,43,2147483647,-2147483646,-2147483648,89,-2147483646,89,-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43};
        HashSet<Long> set = new HashSet<>();
        long record=0l;
        for (long i :nums) {
            set.add(i);
            record += i;
        }
        long re=0l;
        for (Long o :set)
            re+=(Long)o;
        System.out.println((3*re-record)>>1);
    }
}
