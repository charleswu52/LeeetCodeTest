package leetcode_everyday._2022.Jun;

import java.util.*;

/**
 * @author WuChao
 * @create 2022/6/19 13:50
 */
public class _19_508 {
    /**
     * 每日一题：2022/6/19
     * <p>
     * 508. 出现次数最多的子树元素和
     * <p>
     * 难度：medium
     * <p>
     * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
     *
     * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
     * <p>
     * 示例
     * <p>
     * 输入: root = [5,2,-3]
     *
     * 输出: [2,-3,4]
     * <p>
     * 范围
     * <p>
     * 节点数在 [1, 10^4] 范围内
     * -10^5 <= Node.val <= 10^5
     */

    /**
     * 二叉树节点定义
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
    思路：DFS + 哈希表
     */
    int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        List<Integer> list = new ArrayList<>();
        for (int i : map.keySet()) {
            if (map.get(i) == max) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left, map);
        int right = dfs(node.right, map);
        int sum = left + right + node.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        max = Math.max(max, map.get(sum));
        return sum;
    }
}
