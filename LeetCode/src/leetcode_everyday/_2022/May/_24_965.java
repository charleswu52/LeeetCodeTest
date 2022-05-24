package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/24 8:34
 */
public class _24_965 {
    /**
     * 每日一题：2022/5/24
     * <p>
     * 965. 单值二叉树
     * <p>
     * 难度：easy
     * <p>
     * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
     *
     * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
     * <p>
     * 示例
     * <p>
     * 输入：[1,1,1,1,1,null,1]
     *
     * 输出：true
     * <p>
     * 范围
     * <p>
     * 给定树的节点数范围是 [1, 100]。
     * 每个节点的值都是整数，范围为 [0, 99] 。
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
    思路：DFS
     */
    boolean res = true;
    int val = -1;
    public boolean isUnivalTree(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode node) {
        if (node == null || !res) {
            return;
        }
        if (val == -1) {
            val = node.val;
        } else {
            if (node.val != val) {
                res = false;
                return;
            }
        }
        dfs(node.left);
        dfs(node.right);
    }
}
