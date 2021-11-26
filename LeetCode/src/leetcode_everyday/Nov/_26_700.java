package leetcode_everyday.Nov;

/**
 * @author WuChao
 * @create 2021/11/26 14:46
 */
public class _26_700 {
    /**
     * 每日一题：2021/11/26
     * <p>
     * 700. 二叉搜索树中的搜索
     * <p>
     * 难度：easy
     * <p>
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     * 给定二叉搜索树:
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 以及搜索值: 2
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
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

    /*
    思路1：递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    /*
    思路2：迭代
     */

    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

}
