package leetcode_everyday._2022.May;

/**
 * @author WuChao
 * @create 2022/5/16 9:09
 */
public class _16_04_06 {
    /**
     * 每日一题：2022/5/16
     * <p>
     * 面试题 04.06. 后继者
     * <p>
     * 难度：medium
     * <p>
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     *
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     * <p>
     * 示例:
     * <p>
     * 输入: root = [2,1,3], p = 1
     *
     *   2
     *  / \
     * 1   3
     *
     * 输出: 2
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


    TreeNode pre, next, p;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        this.pre = null;
        this.p = p;
        this.next = null;
        dfs(root);
        return next;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre == null) {
            pre = node;
        } else {
            if (pre == p) {
                next = node;
            }
            pre = node;
        }
        dfs(node.right);
    }
}
