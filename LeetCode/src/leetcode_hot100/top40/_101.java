package leetcode_hot100.top40;

/**
 * @author WuChao
 * @create 2021/7/13 11:35
 */
public class _101 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 98. 验证二叉搜索树
     * 难度：medium
     * <p>
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 示例
     * 输入:
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     * 根节点的值为 5 ，但是其右子节点值为 4 。
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * <p>
     * 范围：
     * 1 <= n <= 19
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
    思路：递归
    不要只是判断两个左右子树是否相等，重点是 在递归的时候 左子树往左走，右子树要往右走，同时左子树往右走，右子树往左走
    即递归过程中两个子树走的方向是相反的

     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
    }


}
