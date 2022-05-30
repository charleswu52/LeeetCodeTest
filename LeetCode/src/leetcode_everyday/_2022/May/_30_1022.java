package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/30 8:40
 */
public class _30_1022 {
    /**
     * 每日一题：2022/5/30
     * <p>
     * 1022. 从根到叶的二进制数之和
     * <p>
     * 难度：easy
     * <p>
     * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
     *
     * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
     * <p>
     * 示例
     * <p>
     * 输入：root = [1,0,1,0,1,0,1]
     *
     * 输出：22
     *
     * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     * <p>
     * 范围
     *
     * 树中的节点数在 [1, 1000] 范围内
     * Node.val 仅为 0 或 1
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

    public int sumRootToLeaf(TreeNode root) {
        return  dfs(root,0);

    }

    public int dfs(TreeNode node,int sum) {
        if (node == null) {
            return 0;
        }
        sum = 2 * sum + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        }
        return dfs(node.left, sum) + dfs(node.right, sum);

    }
}
