package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/13 13:37
 */
public class _104 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 102. 二叉树的层序遍历
     * 难度：easy
     * <p>
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
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
    思路：深度优先搜索
    做法1：记录下深度 然后到叶子节点的时候深度再减回去
     */

    int depth;
    int sum;
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        depth = 0;
        sum = 0;
        dfs(root);
        return depth;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        sum++;
        if (node.left == null && node.right == null) {
            depth = Math.max(depth, sum);
            sum--;
            return;
        }
        dfs(node.left);
        dfs(node.right);
        sum--;
    }

    /*
    思路2：深度优先遍历

     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth2(root.left);
            int rightHeight = maxDepth2(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }



}
