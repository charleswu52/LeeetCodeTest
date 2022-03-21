package leetcode_everyday._2022.Mar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/3/21 8:44
 */
public class _21_653 {
    /**
     * 每日一题：2022/3/21
     * <p>
     * 653. 两数之和 IV - 输入 BST
     * <p>
     * 难度：easy
     * <p>
     * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     * <p>
     * 示例1
     * <p>
     * 输入: root = [5,3,6,2,4,null,7], k = 9
     * <p>
     * 输出: true
     * <p>
     * 范围
     * <p>
     * 二叉树的节点个数的范围是 [1, 10^4].
     * -10^4 <= Node.val <= 10^4
     * root 为二叉搜索树
     * -10^5 <= k <= 10^5
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
    思路：中序遍历 + 双指针
     */

    List<Integer> list = new ArrayList<>();
    public boolean findTarget(TreeNode root, int k) {
        inorder(root);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == k) {
                return true;
            }
            if (list.get(left) + list.get(right) < k) {
                left++;
            }else {
                right--;
            }
        }
        return false;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }


}
