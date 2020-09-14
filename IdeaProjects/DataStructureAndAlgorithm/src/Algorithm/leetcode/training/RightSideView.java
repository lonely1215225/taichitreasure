package Algorithm.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(3);
        TreeNode root3 = new TreeNode(5);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(6);
        TreeNode root6 = new TreeNode(8);
        TreeNode root7 = new TreeNode(9);
        TreeNode root8 = new TreeNode(10);
        TreeNode root9 = new TreeNode(11);
        root.left = root1;
        root.right = root2;
        root1.right = root3;
        root2.right = root4;
        root2.left = root5;
        root4.right = root8;
        root5.left = root6;
        root5.right = root7;
        root7.left=root9;
//        List<List<Integer>> level = level(root);
//        System.out.println(level);
        List<Integer> list = rightSideView(root);
        System.out.println(list);
    }

    //     ‰»Î: [1,2,3,null,5,null,4]
//     ‰≥ˆ: [1, 3, 4]
//               1            <---
//             /   \
//            2     3         <---
//            \    / \
//            5   6   4       <---
//               / \   \
//              8   9   10
//                 /
//                11
    List<Integer> list = new ArrayList<>();

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> level = level(root);
        for (int i = 0; i < level.size(); i++)
            list.add(level.get(i).get(level.get(i).size() - 1));
        return list;
    }

    public static List<List<Integer>> level(TreeNode node) {
        TreeNode temp = node;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(node);
        List<List<Integer>> lists = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null)
                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);
                list.add(poll.val);
            }
            lists.add(list);
        }
        return lists;
    }
}