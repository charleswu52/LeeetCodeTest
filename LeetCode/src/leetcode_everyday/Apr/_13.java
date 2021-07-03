package leetcode_everyday.Apr;

/**
 * @author WuChao
 * @since 2021/4/13 上午8:21
 */
public class _13 {
    /**
     * 每日一题：2021/4/13
     * 179. 最大数
     * 难度: easy
     * <p>
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * <p>
     * 示例：
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     *
     * <p>
     * 数据范围：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 109
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
    题目解析：
    实际上是考察二叉搜索树的遍历，这里使用中序遍历，遍历的结果
     */
    int ans = Integer.MAX_VALUE, pre = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {

        inorder(root);
        return ans;
    }

    public void inorder(TreeNode node) {
        if (node != null) {

            inorder(node.left);
            if (pre == Integer.MAX_VALUE) {
                pre = node.val;
            } else {
                ans = Math.min(ans, node.val - pre);
                pre = node.val;
            }
            inorder(node.right);
        }
    }
}
