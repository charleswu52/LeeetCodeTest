package leetcode_hot100.top40;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2021/7/13 8:54
 */
public class _94 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 94. 二叉树的中序遍历
     * 难度：easy
     * <p>
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * <p>
     * 示例
     * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * 输出：6
     * 解释：最大矩形如上图所示。
     * <p>
     * 输入：matrix = [["0"]]
     * 输出：0
     *
     * <p>
     * 范围：
     * rows == matrix.length
     * cols == matrix[0].length
     * 0 <= row, cols <= 200
     * matrix[i][j] 为 '0' 或 '1'
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
    二叉树中序遍历
     */

    List<Integer> res;
    public List<Integer> inorderTraversal(TreeNode root) {
        this.res = new ArrayList<>();
        inorder(root);
        return res;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        res.add(node.val);
        inorder(node.right);
    }
}
