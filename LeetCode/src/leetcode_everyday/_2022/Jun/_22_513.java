package leetcode_everyday._2022.Jun;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author WuChao
 * @create 2022/6/22 10:55
 */
public class _22_513 {
    /**
     * 每日一题：2022/6/22
     * <p>
     * 513. 找树左下角的值
     * <p>
     * 难度：medium
     * <p>
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     *
     * 假设二叉树中至少有一个节点。
     * <p>
     * 示例
     * <p>
     * 输入: root = [2,1,3]
     *
     * 输出: 1
     * <p>
     * 范围
     * <p>
     * 二叉树的节点个数的范围是 [1,10^4]
     * -231 <= Node.val <= 231 - 1
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            /* 方式1
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

             */
            // 方式2
            TreeNode node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            res = node.val;
        }
        return res;
    }

    /*
   思路：DFS
     */

    int curVal = 0;
    int curHeight = 0;
    public int findBottomLeftValue2(TreeNode root) {
        dfs(root, 0);
        return curVal;

    }

    public void dfs(TreeNode node,int height){
        if (node == null) {
            return;
        }
        height++;
        dfs(node.left, height);
        dfs(node.right, height);
        if (height > curHeight) {
            curHeight = height;
            curVal = node.val;
        }
    }


}
