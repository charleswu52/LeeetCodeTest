package leetcode_everyday._2021.Apr;

/**
 * @author WuChao
 * @since 2021/4/27 上午8:10
 */
public class _27 {
    /**
     * 每日一题：2021/4/27
     * 938. 二叉搜索树的范围和
     * 难度: easy
     * <p>
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     *
     * <p>
     * 示例：
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     * <p>
     * 数据范围：
     * 树中节点数目在范围 [1, 2 * 104] 内
     * 1 <= Node.val <= 105
     * 1 <= low <= high <= 105
     * 所有 Node.val 互不相同
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

    int ans;
    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        inOrder(root, low, high);
        return ans;
    }

    public void inOrder(TreeNode root,int low ,int high) {
        if (root != null) {
            inOrder(root.left, low, high);
            if (root.val >= low && root.val <= high) {
                ans += root.val;
            }
            // 剪枝操作，但是时间复杂度没变
            if (root.val > high) {
                return;
            }
            inOrder(root.right, low, high);
        }
    }

    // 上述方法还是要从头遍历一遍二叉搜索树，效率并不高

}