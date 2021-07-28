package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/28 13:12
 */
public class _543 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 543. 二叉树的直径
     * 难度：easy
     * <p>
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     * <p>
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     */

    // 二叉树节点定义
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
    思路：
    深度优先搜索

    以每一个节点为中心节点，计算最长路径（左子树边长 + 右子树边长），更新全局变量max
     */

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root.left == null && root.right==null) {
            return 0;
        }
        int leftSize = root.left == null ? 0 : dfs(root.left) + 1;
        int rightSize = root.right == null ? 0 : dfs(root.right) + 1;
        max = Math.max(max, leftSize + rightSize);
        return Math.max(leftSize, rightSize);
    }
}
