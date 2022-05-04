package leetcode_everyday._2022.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChao
 * @create 2022/5/4 10:55
 */
public class _1_1305 {
    /**
     * 每日一题：2022/5/1
     * <p>
     * 1305. 两棵二叉搜索树中的所有元素
     * <p>
     * 难度：medium
     * <p>
     * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
     * <p>
     * 示例
     * <p>
     * 输入：root1 = [2,1,4], root2 = [1,0,3]
     *
     * 输出：[0,1,1,2,3,4]
     * <p>
     * 范围
     * <p>
     * 每棵树的节点数在 [0, 5000] 范围内
     * -105 <= Node.val <= 105
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
    思路：
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        int MaxSize = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        int size1 = list1.size(), size2 = list2.size(), i = 0, j = 0;
        while (i < size1 || j < size2) {
            int a = i < size1 ? list1.get(i) : MaxSize;
            int b = j < size2 ? list2.get(j) : MaxSize;
            if (a <= b) {
                ans.add(a);
                i++;
            } else {
                ans.add(b);
                j++;
            }
        }
        return ans;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left,list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
