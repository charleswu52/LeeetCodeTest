package chp4;

/**
 * @author WuChao
 * @since 2021/4/28 上午11:18
 */
public class _12 {
    /**
     * 程序员面试金典(version 6) - 面试题 04.12. 求和路径
     * 难度: medium
     * <p>
     * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
     * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * 3
     * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
     *
     * <p>
     * 数据范围：
     * 节点总数 <= 10000
     */

    /*
    解题思路：
    第一步：简化——假设路径必须从根节点开始，但可以在任意结点结束，怎么解决？
    就是使用深度搜索，从根节点开始，向左向右访问子结点，结算每条路径上的总和并比较。注意，就算找到总和，仍然要继续访问这条路径。
    因为这条路径往下可能还有总和为0的一段子路径，这样总和仍为sum。

    第二步：推广——路径可以从任意结点开始，但是“向上”计算是否有符合条件的路径。
    使用数组记录当前的路径，对于每个结点，我们都向“上”检查是否得到相符的总和。也就是，不再要求“从这个结点开始是否会有总和为给定值的路径”，
    而是关注“这个结点是否为总和等于给定值的某条路径的末端”。
    递归访问每个结点n时，将root到n的完整路径传入该函数。随后，这个函数会以相反的顺序，从n到root，将路径上的结点值加起来。
    每个等于给定值的总和，都记录下来。

    **重点在于“逆向思维”。**从上往下访问，遍历整棵树或者处理数组都很麻烦。但是从下往上访问，每个结点就可以只被访问一次，虽然增加了数组访问时间。
     */


    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int pathSum(TreeNode root, int sum) {
        int depth = depth(root);  // 求二叉树的深度
        int[] path = new int[depth]; // 记录二叉树的路径长度

        findSum(root, sum, path, 0);
        return count;
    }

    int count = 0;

    /**
     * 递归查找
     * @param node 当前节点
     * @param sum 目标和
     * @param path 路径
     * @param level 当前节点所在二叉树的层数
     */
    private void findSum(TreeNode node, int sum, int[] path, int level) {
        if (node == null) {
            return;
        }
        // 当前结点插入路径
        path[level] = node.val;
        // 查找以此为终点且总和为sum的路径
        int t = 0;
        for (int i = level; i >= 0; i--) {
            t += path[i];
            if (t == sum) {
                count++;
            }
        }
        // 查找此结点之下的结点
        findSum(node.left, sum, path, level + 1);
        findSum(node.right, sum, path, level + 1);

        // 从路径中移除当前结点
        path[level] = Integer.MIN_VALUE;
    }

    //查找二叉树最大深度
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(node.left), depth(node.right));
        }
    }


}
