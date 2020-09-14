package Algorithm.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LevelOrder {
    //    [3,9,20,null,null,15,7],
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node.left = node1;
        node.right = node2;
        node2.left = node3;
        node2.right = node4;
        List<List<Integer>> lists = levelOrder(node);
        System.out.println(lists);

    }

    public static List<List<Integer>> levelOrder(TreeNode node) {
        if (node==null) return null;
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        List<List<Integer>> lists = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) return lists;
                list.add(poll.val);
                if (poll.left != null)
                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);
            }
            lists.add(list);
        }
        return lists;
    }
}