package leetcode_everyday.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/5/10 上午7:58
 */
public class _10 {
    /**
     * 每日一题：2021/5/10
     * 872. 叶子相似的树
     * 难度: easy
     * <p>
     * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
     * <p>
     * <p>
     * <p>
     * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
     * <p>
     * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
     * <p>
     * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
     *
     *
     * <p>
     * 示例：
     * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
     * 输出：true
     *
     * <p>
     * 数据范围：
     * 给定的两棵树可能会有 1 到 200 个结点。
     * 给定的两棵树上的值介于 0 到 200 之间。
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


    List<Integer> leafSequence;
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        leafSequence = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inOrder(root1);
        System.out.println(leafSequence);
        list1.addAll(leafSequence);
        leafSequence.clear();
        inOrder(root2);
        System.out.println(leafSequence);
        list2.addAll(leafSequence);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                leafSequence.add(node.val);
            } else {
                inOrder(node.left);

                inOrder(node.right);
            }
        }
    }

}
