package leetcode_everyday._2022.Jun;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/6/24 9:37
 */
public class _24_515 {
    /**
     * 每日一题：2022/6/24
     * <p>
     * 515. 在每个树行中找最大值
     * <p>
     * 难度：medium
     * <p>
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * <p>
     * 示例
     * <p>
     * 输入: root = [1,3,2,5,3,null,9]
     * <p>
     * 输出: [1,3,9]
     * <p>
     * 范围
     * <p>
     * 二叉树的节点个数的范围是 [0,10^4]
     * -231 <= Node.val <= 2^31 - 1
     */

    /**
     * 二叉树节点定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
    思路：BFS
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
