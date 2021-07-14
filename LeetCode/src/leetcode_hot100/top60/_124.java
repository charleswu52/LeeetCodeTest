package leetcode_hot100.top60;

public class _124 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 124. 二叉树中的最大路径和
     * 难度：hard
     * <p>
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
     * 该路径 至少包含一个 节点，且不一定经过根节点。
     * <p>
     * 路径和 是路径中各节点值的总和。
     * <p>
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     *
     * <p>
     * 示例：
     * 输入：root = [1,2,3]
     * 输出：6
     * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
     * <p>
     * 输入：root = [-10,9,20,null,null,15,7]
     * 输出：42
     * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     * <p>
     * 数据范围:
     * 树中节点数目范围是 [1, 3 * 104]
     * -1000 <= Node.val <= 1000
     */

    /*
    二叉树节点定义
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
    思路1：递归

    要求当前的最大路径和：根据当前节点的角色，路径可以分为两种情况
    （1） 以当前节点为根节点
        1. 只有当前节点
        2. 当前节点 只有左子树
        3. 当前节点 只有右子树
        4. 当前节点 同时有左子树和右子树
       上面四种情况的最大值就是以当前节点为根的最大路径和
       此最大值与已经保存的最大值进行比较，得到整棵树的最大路径值


    （2）当前节点作为父结点的一个子结点 和父结点连接的话需要取单端的最大值
        也有三种情况
        1. 只有当前节点（父结点只有该一个子结点）
        2. 当前节点 + 左子树
        3. 当前节点 + 左子树
       取这三种情况的最大值

     */
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxValue(root);
        return res;
    }

    public int maxValue(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftValue = maxValue(node.left);
        int rightValue = maxValue(node.right);

        // 四种情况的值
        int case1 = node.val;
        int case2 = node.val + leftValue;
        int case3 = node.val + rightValue;
        int case4 = node.val + leftValue + rightValue;

        // 以当前结点为根的最大值
        int curNodeMaxValue = Math.max(Math.max(case1, case2), Math.max(case3, case4));

        // 当前遍历树的最大值
        res = Math.max(res, curNodeMaxValue);

        // 当前节点作为父结点的一个子结点，去除 case4 的情况的最大值
        return Math.max(case1, Math.max(case2, case3));
    }
}
