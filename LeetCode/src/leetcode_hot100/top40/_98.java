package leetcode_hot100.top40;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/13 9:57
 */
public class _98 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 98. 验证二叉搜索树
     * 难度：medium
     * <p>
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 示例
     * 输入:
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     * 根节点的值为 5 ，但是其右子节点值为 4 。
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * <p>
     * 范围：
     * 1 <= n <= 19
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

    /*
    使用深度优先遍历，中序遍历二叉树，如果是递增的就为TRUE 否则是 FALSE
     */
    List<Integer> store;
    boolean res;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        store = new ArrayList<>();
        res = true;
        inOrder(root);
        return res;
    }

    public void inOrder(TreeNode node) {
        if (node == null || !res) {
            return;
        }
        inOrder(node.left);
        if (store.isEmpty()) {
            store.add(node.val);
        } else {
            if (store.get(store.size() - 1) >= node.val) {
                res = false;
                return;
            } else {
                store.add(node.val);
            }
        }
        inOrder(node.right);
    }

    /*
    思路1：中序遍历改进版
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;
        while (!deque.isEmpty() || root != null) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }



    /*
    思路2：递归
    速度最快！
     */
    public boolean isValidBST3(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode node, long lower, long upper) {

        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}
