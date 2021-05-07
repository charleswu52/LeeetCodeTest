package chp4;

/**
 * @author WuChao
 * @since 2021/4/28 上午11:16
 */
public class _10 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.10. 检查子树
     * 难度: medium
     * <p>
     * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     * <p>
     * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
     *
     * <p>
     * 示例：
     * 输入：t1 = [1, 2, 3], t2 = [2]
     * 输出：true
     * <p>
     * 数据范围：
     * 树的节点数目范围为[0, 20000]。
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    思路：递归的思路解决
     */

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        return isTreeEql(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    /**
     * 递归方法判断两棵树是否相等，相等的条件是
     * @param t1
     * @param t2
     * @return
     */
    public boolean isTreeEql(TreeNode t1, TreeNode t2) {
        if (t1 == t2) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && isTreeEql(t1.left, t2.left) && isTreeEql(t1.right, t2.right);

    }
}
