package leetcode_hot100.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuChao
 * @create 2021/7/26 13:31
 */
public class _437 {
    /**
     * Leetcode 热题 Top 100
     * <p>
     * 437. 路径总和 III
     * 难度：medium
     * <p>
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     *
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * <p>
     * 示例 1:
     * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
     * 输出：3
     * 解释：和等于 8 的路径有 3 条，如图所示。
     * <p>
     * 二叉树的节点个数的范围是 [0,1000]
     * -109 <= Node.val <= 109
     * -1000 <= targetSum <= 1000
     */

    // 二叉树节点定义
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
    思路： 前缀和


     */

    Map<Integer, Integer> prefixMap;
    int target;

    public int pathSum(TreeNode root, int sum) {
        this.prefixMap = new HashMap<>();
        this.target = sum;

        this.prefixMap.put(0, 1);
        return dfs(root, 0);
    }

    public int dfs(TreeNode node, int curSum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        curSum += node.val;
        res += prefixMap.getOrDefault(curSum - target, 0);
        prefixMap.put(curSum, prefixMap.getOrDefault(curSum, 0) + 1);

        int left = dfs(node.left, curSum);
        int right = dfs(node.right, curSum);
        res = res + left + right;
        // 状态恢复,防止左边前缀树影响右边前缀树
        prefixMap.put(curSum, prefixMap.getOrDefault(curSum, 0) - 1);
        return res;
    }
}
