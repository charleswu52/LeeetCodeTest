/**
 * @author WuChao
 * @since 2021/3/27 上午10:01
 */
public class _55_2 {
    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 难度: easy
     * <p>
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     *
     * <p>
     * 例如：
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7]
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回 true 。
     *
     * <p>
     * 数据范围：
     * 1. 节点总数 <= 10000
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     *思路解析：还是与上一题 @code{link  _55_1 } 思路类似
     * 对二叉树做后序遍历，从底至顶返回子树深度，若判定某子树不是平衡二叉树则“剪枝”，直接向上返回
     *
     * 定义一个递归函数 recur(root)函数：
     * 返回值：
     *  1. 当节点root 左 / 右子树的深度差 ≤ 1 ：则返回当前子树的深度，即节点 root 的左/右子树的深度最大值 +1（ max(left, right) + 1 ）；
     *  2. 当节点root 左 / 右子树的深度差 > 2 ：则返回 -1，代表 此子树不是平衡树 。
     * 终止条件：
     *  1.当 root 为空：说明越过叶节点，因此返回高度 0；
     *  2.当左（右）子树深度为 -1 ：代表此树的 左（右）子树 不是平衡树，因此剪枝，直接返回 −1 ；
     *
     * 目标函数：isBalanced(root) 函数：
     *  返回值： 若 recur(root) != -1 ，则说明此树平衡，返回 true ； 否则返回 false。
     */

    /**
     * 判断一棵树是不是平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;

    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {// 左子树不是二叉平衡树
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {//右子树不是二叉平衡树
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;

    }
}
