package chp4;

/**
 * @author WuChao
 * @since 2021/4/27 上午9:11
 */
public class _5 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.05. 合法二叉搜索树
     * 难度: medium
     * <p>
     * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
     * <p>
     * 例如：
     * 输入:
     *     5
     *    / \
     *   1   4
     *     / \
     *    3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *     根节点的值为 5 ，但是其右子节点值为 4 。
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


    TreeNode pre;
    boolean ans;
    public boolean isValidBST(TreeNode root) {
        pre = null;
        ans = true;
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            if (pre == null) {
                pre = root;
            } else {
                if (root.val <= pre.val) {
                    ans = false;
                    return;
                }
                pre = root;
            }
            inOrder(root.right);
        }
    }

}
