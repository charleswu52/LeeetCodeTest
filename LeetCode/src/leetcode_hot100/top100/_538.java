package leetcode_hot100.top100;

/**
 * @author WuChao
 * @create 2021/7/28 11:33
 */
public class _538 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 538. 把二叉搜索树转换为累加树
     * 难度：medium
     * <p>
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * <p>
     * 提醒一下，二叉搜索树满足下列约束条件：
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * <p>
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     *
     *
     *
     * <p>
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * <p>
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     *
     * <p>
     * 输入：nums = [1], target = 1
     * 输出：1
     *
     * <p>
     * 提示：
     * <p>
     * 树中的节点数介于 0 和 104 之间。
     * 每个节点的值介于 -104 和 104 之间。
     * 树中的所有值 互不相同 。
     * 给定的树为二叉搜索树。
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
    题目解析：
    题目看了半天没懂，意思其实是将每个节点的值修改为原来的节点值加上所有大于它的节点的值。

    这样我们只需要反序中序遍历该二叉搜索树，记录过程中的节点值之和，并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。

    思路： 反向中序遍历

     */

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }


}
