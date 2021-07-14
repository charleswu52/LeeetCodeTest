package leetcode_hot100.top60;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/13 14:23
 */
public class _105 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 105. 从前序与中序遍历序列构造二叉树
     * 难度：medium
     * <p>
     * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
     *
     * <p>
     * 示例：
     * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * Output: [3,9,20,null,null,15,7]
     * <p>
     * Input: preorder = [-1], inorder = [-1]
     * Output: [-1]
     * <p>
     * 数据范围；
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder 和 inorder 均无重复元素
     * inorder 均出现在 preorder
     * preorder 保证为二叉树的前序遍历序列
     * inorder 保证为二叉树的中序遍历序列
     */

    /*
    思路1：递归

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

    // 存储中序遍历顺序中每个节点以及对应的下标
    Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder,
                                int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历的第一个就是根节点 下标
        int preorder_root = preorder_left;
        // 在中序遍历中找到根节点 下标
        int inorder_root = map.get(preorder[preorder_root]);

        // 先建立根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);

        // 得到左子树的节点数目
        int size_left_subtree = inorder_root - inorder_left;

        // 递归构造左子树
        root.left = myBuildTree(
                preorder, inorder,
                preorder_left + 1, preorder_left + size_left_subtree,
                inorder_left, inorder_root - 1);
        // 递归构造右子树
        root.right = myBuildTree(
                preorder, inorder,
                preorder_left + size_left_subtree + 1, preorder_right,
                inorder_root + 1, inorder_right);

        return root;
    }


}
