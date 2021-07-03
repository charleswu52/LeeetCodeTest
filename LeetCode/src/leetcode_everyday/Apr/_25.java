package leetcode_everyday.Apr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @since 2021/4/25 上午8:14
 */
public class _25 {
    /**
     * 每日一题：2021/4/25
     * 897. 递增顺序搜索树
     * 难度: easy
     * <p>
     * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，
     * 只有一个右子节点。
     *
     * <p>
     * 示例：
     * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     * <p>
     * 数据范围：
     * 树中节点数的取值范围是 [1, 100]
     * 0 <= Node.val <= 1000
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


    /**
     * 思路1： 中序遍历，并保存遍历过程中的节点
     */
    List<TreeNode> treeNodeList;
    public TreeNode increasingBST(TreeNode root) {

        treeNodeList = new ArrayList<>();
        inOrder(root);
        for (int i = 0; i < treeNodeList.size(); i++) {
            TreeNode node = treeNodeList.get(i);
            if (i + 1 < treeNodeList.size()) {
                node.left = null;
                node.right = treeNodeList.get(i + 1);
            } else {
                node.left = null;
                node.right = null;
            }
        }
        return treeNodeList.get(0);
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            treeNodeList.add(root);
            inOrder(root.right);
        }

    }

    // 改进：时间复杂度还是O(n),空间复杂度为O(1)
    TreeNode head = null, pre = null;
    public TreeNode increasingBST2(TreeNode root) {
        inOrder2(root);

        pre.right = null;
        pre.left = null;
        return head;
    }

    public void inOrder2(TreeNode root) {
        if (root != null) {
            inOrder2(root.left);
            if (head == null) {
                head = root;
            }
            if (pre == null) {
                pre = root;
            } else {
                pre.left = null;
                pre.right = root;
                pre = root;
            }
//            treeNodeList.add(root);
            inOrder2(root.right);
        }

    }
}