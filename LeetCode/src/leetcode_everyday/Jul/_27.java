package leetcode_everyday.Jul;

/**
 * @author WuChao
 * @create 2021/7/27 9:40
 */
public class _27 {
    /**
     * <p> 每日一题：2021/7/27 </p>
     * <p> 671. 二叉树中第二小的节点 </p>
     * <p> 难度: easy </p>
     *
     * <p>
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     * <p>
     * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
     * <p>
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     *
     * </p>
     * <p>示例</p>
     * <p>
     * 输入：target = [5,1,3], arr = [9,4,2,3,4]
     * 输出：2
     * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
     * <p>
     * <p>
     * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
     * 输出：3
     *
     * </p>
     *
     * <p> 范围 </p>
     * <p>
     * 1 <= target.length, arr.length <= 105
     * 1 <= target[i], arr[i] <= 109
     * target 不包含任何重复元素。
     *
     * </p>
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
    思路：DFS搜索
    搜索过程中更新记录最小值和次最小值
     */

    int first = -1;
    int second = -1;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        return second;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (first == -1 || (first != -1 && node.val < first)) {
            first = node.val;
        } else if ((second == -1 && node.val > first) || (second != -1 && node.val < second && node.val > first)) {
            second = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }


}
