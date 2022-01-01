package leetcode_everyday._2021.Nov;

/**
 * @author WuChao
 * @create 2021/11/18 9:33
 */
public class _18_563 {
    /**
     * 每日一题：2021/11/18
     * <p>
     * 563. 二叉树的坡度
     * <p>
     * 难度：easy
     * <p>
     * 给定一个二叉树，计算 整个树 的坡度 。
     *
     * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。
     * 如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
     *
     * 整个树 的坡度就是其所有节点的坡度之和。
     *
     *
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [1,2,3]
     *
     * 输出：1
     *
     * 解释：
     *
     * 节点 2 的坡度：|0-0| = 0（没有子节点）
     * 节点 3 的坡度：|0-0| = 0（没有子节点）
     * 节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
     * 坡度总和：0 + 0 + 1 = 1
     * <p>
     * 范围
     * <p>
     * 树中节点数目的范围在 [0, 104] 内
     * -1000 <= Node.val <= 1000
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
    思路：DFS 深度优先遍历
     */
    int ans = 0;
    public int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sumLeft = dfs(node.left);
        int sumRight = dfs(node.right);
        ans += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + node.val;
    }


}
