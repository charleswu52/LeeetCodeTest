package chp4;

/**
 * @author WuChao
 * @since 2021/4/27 上午9:58
 */
public class _6 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.06. 后继者
     * 难度: medium
     * <p>
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     * <p>
     * 例如：
     * 输入:
     *    输入: root = [5,3,6,2,4,null,null,1], p = 6
     *
     *       5
     *      / \
     *     3   6
     *    / \
     *   2   4
     *  /
     * 1
     *
     * 输出: null
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
    TreeNode ans;
    TreeNode target;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        pre = null;
        ans = null;
        target = p;
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            if (pre == null) {
                pre = root;
            } else {
                if (pre== target) {
                    ans = root;

                }
                    pre = root;

            }
            inOrder(root.right);
        }
    }
}
