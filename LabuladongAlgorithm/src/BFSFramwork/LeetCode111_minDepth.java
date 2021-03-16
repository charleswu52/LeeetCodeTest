package BFSFramwork;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WuChao
 * @since 2021/3/16 下午12:50
 */
public class LeetCode111_minDepth {
    /**
     * BFS学习框架
     * 案例
     * 111. 二叉树的最小深度
     * 难度：easy
     * <p>
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明：叶子节点是指没有子节点的节点。
     *
     * <p>
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
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

    // 求二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);  // 根节点入队
        // 根节点本身就是一层，初始化为1
        int depth = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            /* 将当前队列中的所有节点向四周扩散*/
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                /* 判断能否到达终点*/
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                /* 将cur的相邻节点加入队列*/
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            /* 增加步数*/
            depth++;
        }
        return depth;
    }


}
