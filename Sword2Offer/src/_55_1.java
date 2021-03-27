/**
 * @author WuChao
 * @since 2021/3/27 上午9:04
 */
public class _55_1 {
    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 难度: easy
     * <p>
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     *
     * <p>
     * 例如：
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     * <p>
     * 数据范围：
     * 1. 节点总数 <= 10000
     */

    /**
     * 二叉树的定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归回溯
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
