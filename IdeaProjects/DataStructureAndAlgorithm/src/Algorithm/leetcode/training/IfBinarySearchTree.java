package Algorithm.leetcode.training;

import java.util.Arrays;
import java.util.Scanner;

public class IfBinarySearchTree {
    static IfBinarySearchTree tree = new IfBinarySearchTree();
    //    static int[] nums={10,5,15,3,7,13,18};
    String input = "None";
    int[] nums;

    public IfBinarySearchTree() {
        if (input.equals("None"))
            nums = null;
        else {
            char[] chars = input.toCharArray();
            nums = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                nums[i] = chars[i] - '0';
            }
        }
    }

    public static void main(String[] args) {
        String s = String.valueOf(tree.isBinarySearchTree(0));
        System.out.println(s.substring(0, 1).toUpperCase() + s.substring(1));
    }

    public boolean isBinarySearchTree(int index) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        if (((2 * index + 1) < nums.length) && (nums[index] < nums[2 * index + 1] || nums[index] > nums[2 * index + 2])) {
            return false;
        }
        if ((2 * index + 1) < nums.length) {
            if (!isBinarySearchTree(2 * index + 1)) return false;
        }
        if ((2 * index + 2) < nums.length) {
            return isBinarySearchTree(2 * index + 2);
        }
        return true;
    }
}
