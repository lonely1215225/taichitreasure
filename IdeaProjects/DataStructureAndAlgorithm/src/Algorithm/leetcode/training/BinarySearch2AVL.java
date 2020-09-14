package Algorithm.leetcode.training;

import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearch2AVL {
    public static void main(String[] args) {
        int[] nums = {14, 9, 16, 2, 13};

        List<Integer> list = new ArrayList();

        list=toList(list,toBST(nums));
        System.out.println(list);
        TreeNode balance = balance(list, 0, list.size() - 1);

        System.out.println(levelTraversal(balance));
    }


    private static TreeNode toBST(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            TreeNode temp = root;
            while (true) {
                if (nums[i] < temp.val) {
                    if (temp.left == null) {
                        temp.left = new TreeNode(nums[i]);
                        break;
                    } else temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = new TreeNode(nums[i]);
                        break;
                    } else temp = temp.right;
                }
            }
        }
        return root;
    }

    static List toList(List<Integer> list, TreeNode node) {
        if (node == null) return null;
        if (node.left != null) toList(list, node.left);
        list.add(node.val);
        if (node.right != null) toList(list, node.right);
        return list;
    }

   static List levelTraversal(TreeNode node) {
        List<Integer> list=new ArrayList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        if (node == null) return null;
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) queue.add(poll.left);
            if (poll.right != null) queue.add(poll.right);
        }
        return list;
    }

    static TreeNode balance(List<Integer> list, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        int val = 0;
        if (list != null) val = list.get(mid);
        else return null;
        TreeNode newNode = new TreeNode(val);
        newNode.left = balance(list, l, mid - 1);
        newNode.right = balance(list, mid + 1, r);
        return newNode;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}