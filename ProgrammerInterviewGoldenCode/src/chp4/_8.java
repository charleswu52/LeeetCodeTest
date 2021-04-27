package chp4;

/**
 * @author WuChao
 * @since 2021/4/27 上午10:38
 */
public class _8 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.08. 首个共同祖先
     * 难度: medium
     * <p>
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     *
     * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *     3
     *    / \
     *   5   1
     *  / \ / \
     * 6  2 0  8
     *   / \
     *  7   4
     *
     *
     * <p>
     * 示例：
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     *
     * <p>
     * 数据范围：
     */

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  /*
  本题可使用模板方法解决诸多问题，
   */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        if (left == null && right == null) {
            return null;
        }
        return left == null ? right : left;
    }


}
