package leetcode_hot100.top60;

/**
 * @author WuChao
 * @create 2021/7/13 14:43
 */
public class _114 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 114. 二叉树展开为链表
     * 难度：medium
     * <p>
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 示例：
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     *
     * 输入：root = []
     * 输出：[]
     *
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * 数据范围；
     * 树中结点数在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     *
     * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
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
    思路：前序遍历+寻找前驱节点
    最优做法：空间复杂度O(1)

    对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
    然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，
    续处理链表中的下一个节点，直到所有节点都处理结束。

    动图参考 https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/
     */

    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode next = cur.left;
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }


}
