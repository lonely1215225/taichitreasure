package Algorithm.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwo {
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 2, 1, 8, 7, 9,5,0,0,10};
        //res(10,nums);//1,2,2,2,3,5,7,8,9
        Arrays.sort(nums);
        three(10, nums);
    }

    public static void three(int sum, int[] nums) {
        Map<Integer, Integer> m0 = new HashMap<>();
        res(sum - nums[0], 0,nums[0], nums,m0);
        for (int i = 1; i < nums.length-3; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            if (nums[i]==nums[i-1]) continue;
            res(sum - nums[i], i,nums[i], nums,map);
        }
    }

    public static void res(int sum, int j,int source, int[] nums,Map<Integer,Integer> map) {
        for (int i = j+1; i < nums.length; i++) {
            int temp = sum - nums[i];
            if (map.get(temp) != null && map.get(nums[i]) == null && nums[i] == map.get(temp)) {
                System.out.println(source + " " + nums[i] + " " + temp);
            }
            map.put(nums[i], temp);//4:6     6:4
        }
    }
}
