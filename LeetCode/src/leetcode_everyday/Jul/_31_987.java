package leetcode_everyday.Jul;

import java.util.*;

/**
 * @author WuChao
 * @create 2021/7/31 7:45
 */
public class _31_987 {
    /**
     * 每日一题：2021/7/31
     * 987. 二叉树的垂序遍历
     * 难度: hard
     * <p>
     * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
     *
     * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
     *
     * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。
     * 如果同行同列上有多个结点，则按结点的值从小到大进行排序。
     *
     * 返回二叉树的 垂序遍历 序列。
     *
     * 示例
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[9],[3,15],[20],[7]]
     * 解释：
     * 列 -1 ：只有结点 9 在此列中。
     * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
     * 列  1 ：只有结点 20 在此列中。
     * 列  2 ：只有结点 7 在此列中。
     *
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[[4],[2],[1,5,6],[3],[7]]
     * 解释：
     * 列 -2 ：只有结点 4 在此列中。
     * 列 -1 ：只有结点 2 在此列中。
     * 列  0 ：结点 1 、5 和 6 都在此列中。
     *           1 在上面，所以它出现在前面。
     *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
     * 列  1 ：只有结点 3 在此列中。
     * 列  2 ：只有结点 7 在此列中。
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
    深度有限搜索  + 哈希表
    嵌套hash表，先以列为key，value也是一个hash，里层的hash以row为key，value就是一个list列表

     */
    Map<Integer, Map<Integer, List<Integer>>> listMap = new TreeMap<>(Integer::compareTo);

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, 0, 0);

        Iterator<Map.Entry<Integer, Map<Integer, List<Integer>>>> iterator = listMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Map<Integer, List<Integer>>> next = iterator.next();
            List<Integer> t = new ArrayList<>();
            Iterator<Map.Entry<Integer, List<Integer>>> iterator1 = next.getValue().entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<Integer, List<Integer>> next1 = iterator1.next();
                List<Integer> value = next1.getValue();
                value.sort(Integer::compareTo);
                t.addAll(value);
            }
            res.add(t);
        }

        return res;


    }

    public void dfs(TreeNode node, int row, int col) {
        if (node == null) {
            return;
        }
        if (listMap.containsKey(col)) {
            Map<Integer, List<Integer>> map = this.listMap.get(col);
            if (map.containsKey(row)) {
                map.get(row).add(node.val);
            } else {
                map.put(row, new ArrayList<Integer>() {{
                    add(node.val);
                }});
            }
            listMap.put(col, map);

        } else {
            Map<Integer, List<Integer>> map = new TreeMap<>(Integer::compareTo);
            map.put(row, new ArrayList<Integer>() {{
                add(node.val);
            }});
            listMap.put(col, map);
        }
        dfs(node.left, row + 1, col - 1);
        dfs(node.right, row + 1, col + 1);
    }
}
