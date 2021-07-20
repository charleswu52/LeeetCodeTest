package leetcode_hot100.top80;

/**
 * @author WuChao
 * @create 2021/7/20 8:52
 */
public class _226 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 226. 翻转二叉树
     * 难度：easy
     * <p>
     *  翻转一棵二叉树。
     *
     * <p>
     * 示例：
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
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
    思路： 递归
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        reverseTree(root);
        return root;
    }

    public void reverseTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        reverseTree(node.left);
        reverseTree(node.right);

    }

}
