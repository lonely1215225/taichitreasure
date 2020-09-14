package Algorithm.leetcode.training;

public class MaxGain {
    int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        MaxGain maxGain = new MaxGain();
        TreeNode node = new TreeNode(10);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node.left=node1;
        node.right=node2;
        node2.left=node3;
        node2.right=node4;
        int i = maxGain.maxPathSum(node);
        System.out.println(i);
    }
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // �ݹ���������ӽڵ�������ֵ
        // ֻ���������ֵ���� 0 ʱ���Ż�ѡȡ��Ӧ�ӽڵ�
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // �ڵ�����·����ȡ���ڸýڵ��ֵ��ýڵ�������ӽڵ�������ֵ
        int priceNewpath = node.val + leftGain + rightGain;

        // ���´�
        maxSum = Math.max(maxSum, priceNewpath);

        // ���ؽڵ�������ֵ
        return node.val + Math.max(leftGain, rightGain);
    }
}
