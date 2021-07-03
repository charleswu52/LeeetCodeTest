package leetcode_everyday.May;

import org.junit.Test;

/**
 * @author WuChao
 * @since 2021/5/26 下午4:08
 */
public class _26_2 {
    /**
     * 每日一题：2021/5/26
     * 671. 二叉树中第二小的节点
     * 难度: easy
     * <p>
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * <p>
     * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
     * <p>
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     *
     *
     * <p>
     * 示例：
     * 输入：root = [2,2,5,null,null,5,7]
     * 输出：5
     * 解释：最小的值是 2 ，第二小的值是 5 。
     * <p>
     * 输入：root = [2,2,2]
     * 输出：-1
     * 解释：最小的值是 2, 但是不存在第二小的值。
     * <p>
     * 数据范围：
     * 树中节点数目在范围 [1, 25] 内
     * 1 <= Node.val <= 231 - 1
     * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
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

    int res = -1;
    int rootVal;
    public int findSecondMinimumValue(TreeNode root) {
        rootVal = root.val;
        travel(root);
        return res;


    }

    public void travel(TreeNode node) {
        if (node != null) {
            if (node.val != rootVal) {
                if (res == -1) {
                    res = node.val;
                } else {
                    res = Math.min(res, node.val);
                    return;
                }
            }
            travel(node.left);
            travel(node.right);

        }
    }

    @Test
    public void test() {
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(5);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(7);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        System.out.println(findSecondMinimumValue(a));
    }
}
