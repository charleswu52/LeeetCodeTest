

/**
 * @author WuChao
 * @since 2021/3/17 上午9:14
 */
public class _27 {
    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 难度: easy
     * <p>
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 例如输入：
     *
     *       4
     *      /  \
     *      2   7
     *     / \  / \
     *    1  3 6  9
     * 镜像输出：
     *
     *        4
     *      /  \
     *     7    2
     *    / \   / \
     *   9  6  3   1
     *
     * <p>
     * 示例：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * <p>
     * 数据范围：
     * 0 <= 节点个数 <= 10000
     */


    // 二叉树的定义
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        preOrder(root);
        return root;

    }

    public void preOrder(TreeNode treeNode) {
        if (treeNode != null) {

            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;

            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }
}
